package com.example.basiccrud.dto;

import lombok.Data;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private int age;
    private String address;
}
