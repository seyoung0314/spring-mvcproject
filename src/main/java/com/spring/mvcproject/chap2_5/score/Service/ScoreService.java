package com.spring.mvcproject.chap2_5.score.Service;


import com.spring.mvcproject.chap2_5.score.dto.request.ScoreCreateDto;
import com.spring.mvcproject.chap2_5.score.dto.response.ScoreDetailDto;
import com.spring.mvcproject.chap2_5.score.dto.response.ScoreListDto;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import com.spring.mvcproject.chap2_5.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

// 역할 : 컨트롤러의 요청을 데이터베이스에 저장하기 쉽게 변환
//       중간 핵심 계산, 데이터 변환처리를 수행, 트랜잭션 처리
@Service
public class ScoreService {

    private ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    // 목록조회 요청 핵심 로직 처리
    public List<ScoreListDto> getList(String sort) {

        List<Score> scoreList = scoreRepository.findAll(sort);

        // 원본 성적 리스트를 클라이언트가 우너하는 모양으로 변환(dto)
        List<ScoreListDto> responseData = scoreList.stream()
                .map(score -> new ScoreListDto(score))
                .collect(Collectors.toList());


        // 석차 처리
        calculatorRank(responseData);

        //정렬 파라미터 처리
        responseData.sort(getScoreComparator(sort));

        // 컨트롤러에게 정제 데이터 반환
        return responseData;
    }

    // 성적 단일 조회 핵심 비즈니스 로직 처리
    public ScoreDetailDto getDetail(Long id) {

        Score score = scoreRepository.findOne(id);

        // 예외처리
        if (score == null) {
            throw new IllegalStateException(id + "성적정보가 존재하지 않습니다.");
        }

        // 석차와 총 학생수를 구하기 위해 락생 목록을 가져옴
        List<Score> scoreList = new ArrayList<>(scoreRepository.findAll(null));

        scoreList.sort(Comparator.comparing((Score s) -> s.getKor() + s.getEng() + s.getMath()).reversed());

        ScoreDetailDto responseDto = new ScoreDetailDto(score, scoreList.size());

        for (Score s : scoreList) {
            if (s.getId().equals(responseDto.getId())) {
                responseDto.setRank(scoreList.indexOf(s) + 1);
            }
        }
        return responseDto;
    }

    // 성적정보 생성 비즈니스로직 처리
    public Score create(ScoreCreateDto dto){
        // ScoreCreateDto 를 Score로 변환하는 작업
        Score score = dto.toEntity();

        scoreRepository.save(score);

        return score;
    }

    // 성적정보 삭제 비지니스로직 처리
    public void remove(Long id){
        boolean removed = scoreRepository.deleteById(id);

        if(!removed){
            throw new IllegalStateException("해당 성적정보는 존재하지 않습니다. id = "+id);
        }
    }

    private void calculatorRank(List<ScoreListDto> responseList) {
        responseList.sort(comparing(ScoreListDto::getAverage).reversed());

        for (ScoreListDto dto : responseList) {
            dto.setRank(responseList.indexOf(dto) + 1);
        }
    }

    private static Comparator<ScoreListDto> getScoreComparator(String sort) {
        Comparator<ScoreListDto> comparing = comparing(ScoreListDto::getId);

        switch (sort) {
            case "id":
                comparing = comparing(ScoreListDto::getId);
                break;
            case "name":
                comparing = comparing(ScoreListDto::getMaskingName);
                break;
            case "average":
                comparing = Comparator.comparingDouble(ScoreListDto::getAverage).reversed();
                break;
            default:
                break;
        }
        return comparing;
    }
}
