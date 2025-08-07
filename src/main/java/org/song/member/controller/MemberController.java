package org.song.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.song.global.lib.Utis;
import org.song.member.services.JoinService;
import org.song.member.validators.JoinValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/member")
public class MemberController {
    private final JoinService service;
    private final JoinValidator validator;
    private final Utis utis;

    // 회원 가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 응답
    public void join(@Valid @RequestBody RequestJoin form , Errors errors){
        validator.validate(form, errors);
        if(errors.hasErrors()){
            throw new BadRequestException(utis.getErrorMessages(errors));
        }
        service.process(form);
    }
}
