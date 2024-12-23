package com.spring.mvcproject.chap2_5.score.repository;

import com.spring.mvcproject.chap2_5.score.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 역할 :  성적정보를 메모리에서 관리하는 역할
@Repository
public class ScoreMemoryRepo implements ScoreRepository {

    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    public ScoreMemoryRepo() {
        Score s1 = new Score(nextId++, "철수", 100, 100, 100);
        Score s2 = new Score(nextId++, "맹구", 55, 95, 15);
        Score s3 = new Score(nextId++, "유리", 4, 100, 40);

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
    }


    @Override
    public List<Score> findAll(String sort) {
        //저장된 성적정보를 모두 읽어옴
        List<Score> scoreList = new ArrayList<>(scoreStore.values());
        return scoreList;
    }

    @Override
    public Score findOne(Long id) {
        return scoreStore.get(id);
    }

    @Override
    public void save(Score score) {
        score.setId(nextId++);
        scoreStore.put(score.getId(), score);
    }

    @Override
    public boolean deleteById(Long id) {
        Score removed = scoreStore.remove(id);
        return removed != null;
    }

}
