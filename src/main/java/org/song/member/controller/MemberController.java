package org.song.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.song.member.services.JoinService;
import org.song.member.validators.JoinValidator;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/member")
public class MemberController {
    private final JoinService service;
    private final JoinValidator validator;

    // 회원 가입
    @PostMapping
    public void join(@Valid RequestJoin form , Errors errors){

    }
}
