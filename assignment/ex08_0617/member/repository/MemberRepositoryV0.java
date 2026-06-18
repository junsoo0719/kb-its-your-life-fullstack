package org.example.kb7spring.member.repository;

import org.example.kb7spring.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryV0 {
    public List<Member> findAll() {
        List<Member> memberList = new ArrayList<>();

        memberList.add(new Member(1L, "ronaldo@gg.com", "1985. 02. 05", "우리형", "SSS", 3000000000000000L));
        memberList.add(new Member(1L, "sjk@gg.com", "1985. 09. 19", "송중기", "A", 30000000000L));
        memberList.add(new Member(1L, "xenosign@gg.com", "1985. 11. 18", "이효석", "B", 300L));

        return memberList;
    }
}
