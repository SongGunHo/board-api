package org.song.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.song.global.excepotion.BadRequestException;
import org.song.global.lib.Utis;
import org.song.member.entityes.Member;
import org.song.member.jwt.TokenService;
import org.song.member.libs.MemberUtil;
import org.song.member.services.JoinService;
import org.song.member.validators.JoinValidator;
import org.song.member.validators.TokenValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원 api", description = "회원 가입 , 회원 인증 토큰 발급 기능 제공")
@RequestMapping("/api/v1/member")
public class MemberController {
    private final JoinService service;
    private final JoinValidator validator;
    private final Utis utis;
    private final TokenValidator token;
    private final TokenService tos;
    private final MemberUtil memberUtil;

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

    /**
     * 회원 계정 (이메일 비밀 번호) 으로 jwt 토큰 발급
     *
     *
     * @return
     */
    @Operation(summary = "회원 인증 처리", description = "이메일과 비밀번호로 인증한 후 회원 전용 요청을 보낼수 있는 토큰(JWT)을 발급")
    @Parameters({
            @Parameter(name="email", required = true, description = "이메일"),
            @Parameter(name="password", required = true, description = "비밀번호")
    })
    @ApiResponse(responseCode = "200", description = "인증 성공시 토큰(JWT)발급")
    @PostMapping("/token")
    public String token(@Valid @RequestBody RequestToken form, Errors errors) {

        token.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utis.getErrorMessages(errors));
        }

        return tos.create(form.getEmail());
    }

    @Operation(summary = "로그인 상태인 회원 정보를 조회", method = "GET")

    @GetMapping //get / api/ v1/member
    @PreAuthorize("isAuthenticated()")
    public Member myinfo(){
        return memberUtil.getMember();
    }
}
