package com.example.basiccrud.service;

import com.example.basiccrud.dto.MemberDto;
import com.example.basiccrud.entity.Member;
import com.example.basiccrud.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public List<Member> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDto> dtoList = new ArrayList<>();

        for (int i = 0; i < memberList.stream().count(); i++) {
            dtoList.add(memberList.get(i));
        }

        return memberList;
    }

}
