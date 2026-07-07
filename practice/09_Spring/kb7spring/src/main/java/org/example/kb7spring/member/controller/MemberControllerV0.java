package org.example.kb7spring.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.member.service.MemberServiceV0;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member/v0")
public class MemberControllerV0 {
    private final MemberServiceV0 memberServiceV0 = MemberServiceV0.getInstance();

    @GetMapping("")
    public String home() {
        log.info("==================> MemberControllerV0, /");
        return "/member/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        log.info("==================> MemberControllerV0, /list");
        model.addAttribute("memberList", memberServiceV0.getMemberList());
        return "/member/list";
    }
}
