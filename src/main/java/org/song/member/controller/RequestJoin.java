package org.song.member.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestJoin {

    @NotBlank
    @Email
    private String email; // 이메일

    private String password; // 패스워드
    private String confirmPassword; // 2차 패스워드
    private String name; // 이름
    private String mobile;// 전화번호
    private boolean termsAgree; // 약관 동의


}
