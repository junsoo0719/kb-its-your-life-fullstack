package org.example.kb7spring.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.member.service.MemberServiceV0;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    private final MemberServiceV0 memberServiceV0 =  MemberServiceV0.getInstance();

    @GetMapping("/")
    public String home(Model model) {
        log.info("====================> HomeController /");
        model.addAttribute("memberList", memberServiceV0.getMemberList());
        return "index";
    }
}
