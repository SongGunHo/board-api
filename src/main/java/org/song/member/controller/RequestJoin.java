package org.song.member.controller;

import lombok.Data;

@Data
public class RequestJoin {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String mobile;
    private boolean termsAgree; // 약관 동의


}
