package com.example.basiccrud.dto;

import com.example.basiccrud.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    @Size(min = 2, message = "이름은 2자 이상 입력 가능")
    private String name;
    @Range(min = 0, max = 120, message = "나이는 0세부터 120세")
    private int age;
    @NotBlank(message = "주소 필수")
    private String address;

    //Entity를 Dto로
    public static MemberDto fromEntity(Member entity){
        return new MemberDto(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getAddress()
        );
    }
    //Dto -> entity
    public static Member fromDto(MemberDto dto){
        Member member = new Member();
        member.setId(dto.getId());
        member.setName(dto.getName());
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        return member;
    }
}
