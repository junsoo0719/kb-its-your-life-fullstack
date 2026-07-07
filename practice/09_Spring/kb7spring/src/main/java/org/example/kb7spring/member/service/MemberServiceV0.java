package org.example.kb7spring.member.service;

import org.example.kb7spring.member.domain.Member;
import org.example.kb7spring.member.dto.MemberDto;
import org.example.kb7spring.member.repository.MemberRepositoryV0;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceV0 {
    private static MemberServiceV0 instance;
    private final MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    private MemberServiceV0() {};

//    static {
//        instance = new MemberServiceV0();
//    }

    public static MemberServiceV0 getInstance() {
        if (instance == null) {
            instance = new MemberServiceV0();
        }

        return instance;
    }

    public List<MemberDto> getMemberList() {
        List<Member> entityList = memberRepositoryV0.findAll();
        List<MemberDto> dtoList = new ArrayList<>();

        for (Member member : entityList) {
            MemberDto dto = new MemberDto();
            dto.setName(member.getName());
            dto.setEmail(member.getEmail());
            dtoList.add(dto);
        }

        return dtoList;
    }
}
