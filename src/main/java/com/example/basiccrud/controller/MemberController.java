package com.example.basiccrud.controller;

import com.example.basiccrud.dto.MemberDto;
import com.example.basiccrud.entity.Member;
import com.example.basiccrud.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/insertForm")
    public String insertFormView(Model model){
        model.addAttribute("memberDto",new MemberDto());
        return "insertMember";
    }

    @PostMapping ("/insert")
    public String insert(
            @Valid MemberDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        //받은 dto를 서비스에 넘겨주고 저장
        if(bindingResult.hasErrors()){
            log.info("================== valiError ==================");
            return "insertMember";
        }
        memberService.saveMember(dto);
        redirectAttributes.addFlashAttribute("msg","신규 데이터 입력");

        return "redirect:/member/view";
    }

    @GetMapping("/view")
    public String showMember(Model model){
        List<MemberDto> memberList = memberService.findAllMembers();
        System.out.println(memberList);
        model.addAttribute("list",memberList);
        return "showMember";
    }

    @GetMapping("/delete/{id}")
    public String deleteMemger(@PathVariable("id")Long id,
                               RedirectAttributes redirectAttributes){
        memberService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg","삭제완료");
        return "redirect:/member/view";
    }

    @GetMapping("/update/{id}")
    public String updateFormView(@PathVariable("id")Long id,
                                 Model model){
        MemberDto dto = memberService.findById(id);
        log.info("=============== dto ===============: " + dto);
        model.addAttribute("member",dto);
        return "updateMember";
    }
    @PostMapping("/update")
    public String update(MemberDto dto,
                         RedirectAttributes redirectAttributes){
        //수정 요청
        log.info("################### DTO : ###################" + dto);
        memberService.updateMember(dto);
        //메시지 전송
        redirectAttributes.addFlashAttribute("msg","수정 완료");
        return "redirect:/member/view";
    }
}