package org.song.member.controller;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestJoin {

    @NotBlank
    @Email
    private String email; // 이메일
    @NotBlank
    @Size(min=8)
    private String password; // 패스워드

    @NotBlank
    private String confirmPassword; // 2차 패스워드
    @NotBlank
    private String name; // 이름
    @NotBlank // string 에서만 가능 하다
    private String mobile;// 전화번호
    @AssertTrue // 항상  true 이여 야 된다 false 은 AssertFalse 있다
    private boolean termsAgree; // 약관 동의


}
