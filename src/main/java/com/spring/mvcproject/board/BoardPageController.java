package com.spring.mvcproject.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    @GetMapping("/list")
    public String list() {
        return "/board/list-page";
    }

    @GetMapping("/write")
    public String write() {
        return "/board/write-page";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable Long id
            , Model model
    ) {
        model.addAttribute("id",id);
        return "/board/detail-page";
    }
}
