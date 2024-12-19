package com.spring.mvcproject.chap2_5.score.routes;


import com.spring.mvcproject.chap2_5.score.entity.Score;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

// 성적관리에 필요한 jsp 파일들을 포워딩 하는 컨트롤러
@Controller
public class ScorePageController {

//    @GetMapping("/score/page")
//    public String scorePage(Model model){
//        model.addAttribute("title","성적 관리");
//        model.addAttribute("foods", List.of("떡볶이","오렌지"));
//
//        //해당 jsp 파일의 경로를 적음
//        return "score/score-page";
//    }

    @GetMapping("/score/page")
    public ModelAndView scorePage(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("title","성적 관리");
        mv.addObject("foods", List.of("떡볶이","오렌지"));

        mv.setViewName("score/score-page");
        //해당 jsp 파일의 경로를 적음
        return mv;
    }

    @GetMapping("/scores")
    public String scorePageSort(){
        //해당 jsp 파일의 경로를 적음
        return "score/score-page";
    }

}
