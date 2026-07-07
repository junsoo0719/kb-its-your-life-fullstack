package org.example.kb7spring.member.repository;

import org.example.kb7spring.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepositoryV1 implements MemberRepository {
    public List<Member> findAll() {
        List<Member> memberList = new ArrayList<>();

        memberList.add(new Member(1L, "ronaldo@gg.com", "1985. 02. 05", "우리형", "SSS", 300000000000L));
        memberList.add(new Member(2L, "sjk@gg.com", "1985. 09. 19", "송중기", "A", 3000000000L));
        memberList.add(new Member(3L, "xenosign@gg.com", "1985. 11. 18", "이효석", "A-", 30L));

        return memberList;
    }
}
