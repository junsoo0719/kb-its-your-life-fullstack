package org.example.kb7spring.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.kb7spring.member.service.MemberServiceV0;
import org.example.kb7spring.member.service.MemberServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member/v1")
public class MemberControllerV1 {
    private final MemberServiceV1 memberService;

    @Autowired
    public MemberControllerV1(MemberServiceV1 memberServiceV1) {
        this.memberService = memberServiceV1;
    }

    @GetMapping("")
    public String home() {
        log.info("==================> MemberControllerV0, /");
        return "/member/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        log.info("==================> MemberControllerV0, /list");
        model.addAttribute("memberList", memberService.getMemberList());
        return "/member/list";
    }
}
