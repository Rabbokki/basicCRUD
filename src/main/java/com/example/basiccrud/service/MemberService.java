package com.example.basiccrud.service;

import com.example.basiccrud.dto.MemberDto;
import com.example.basiccrud.entity.Member;
import com.example.basiccrud.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public List<MemberDto> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
//        List<MemberDto> dtoList = new ArrayList<>();


        return memberList.stream().map(x->
                MemberDto.fromEntity(x)).toList();
//        for (int i = 0; i < memberList.size(); i++) {
//            MemberDto dto = new MemberDto();
//            dto.setId(memberList.get(i).getId());
//            dto.setName(memberList.get(i).getName());
//            dto.setAge(memberList.get(i).getAge());
//            dto.setAddress(memberList.get(i).getAddress());
//
//            dtoList.add(dto);
//        }

//        return dtoList;
    }

    public void saveMember(MemberDto dto) {
        //dto -> entity
        Member member = MemberDto.fromDto(dto);

        //저장요청
        memberRepository.save(member);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto findById(Long id) {
        // id로 db에서 검색
//        Member member = memberRepository.findById(id).orElse(null);
//        //dto로 변환
//        if(!ObjectUtils.isEmpty(member)){
//            return MemberDto.fromEntity(member);
//        }else {
//            return null;
//        }
        return memberRepository.findById(id).stream()
                .map(x->MemberDto.fromEntity(x)).findAny().orElse(null);
    }

    public Member updateMember(MemberDto dto) {
        Member member = MemberDto.fromDto(dto);
        //수정 처리
        //save() : 해당 아이디가 존재하면 수정 없으면 새로 저장
        return memberRepository.save(member);
    }
}
