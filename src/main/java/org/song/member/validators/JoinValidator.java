package org.song.member.validators;


import lombok.RequiredArgsConstructor;
import org.song.member.controller.RequestJoin;
import org.song.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Lazy
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

    private final  MemberRepository repository;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class); // 클래스를 인테페이스 클래스로 다시 구현 한다
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        /*
        *  1. 이메일 중복 여부
        *  2. 비밀 번호 확인 일치 여부
        *   3. 휴대 번호 형식 검증
        *
        * */

    }
}
