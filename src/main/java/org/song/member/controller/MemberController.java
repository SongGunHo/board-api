package org.song.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.song.global.excepotion.BadRequestException;
import org.song.global.lib.Utis;
import org.song.member.services.JoinService;
import org.song.member.validators.JoinValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 api", description = "회원 가입 , 회원 인증 토큰 발급 기능 제공")
@RequestMapping("/api/vi/member")
public class MemberController {
    private final JoinService service;
    private final JoinValidator validator;
    private final Utis utis;

    // 회원 가입
    @Operation(summary = "회원 가입",  method = "Post")
    @ApiResponse(responseCode = "201", description = "회원 가입 성공시 201 응답 검증 실패시 404 ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 응답
    public void join(@Valid @RequestBody RequestJoin form , Errors errors) {
        validator.validate(form, errors);
        if(errors.hasErrors()){
            throw new BadRequestException(utis.getErrorMessages(errors));
        }
        service.process(form);
    }
}
