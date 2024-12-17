package com.spring.mvcproject.chap2_5.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 성적관리에 필요한 jsp 파일들을 포워딩 하는 컨트롤러
@Controller
public class ScorePageController {

    @GetMapping("/score/page")
    public String scorePage(){
        //해당 jsp 파일의 경로를 적음
        return "score/score-page";
    }
    @GetMapping("/scores")
    public String scorePageSort(){
        //해당 jsp 파일의 경로를 적음
        return "score/score-page";
    }

}
