package org.example.kb7spring.member.mapper;

import org.example.kb7spring.member.domain.Member;

import java.util.List;

public interface MemberMapper {
    List<Member> findAll();
}
