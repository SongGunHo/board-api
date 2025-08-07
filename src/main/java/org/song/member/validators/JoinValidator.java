package org.song.member.validators;


import lombok.RequiredArgsConstructor;
import org.song.global.validators.MobileValidator;
import org.song.global.validators.PasswordValidator;
import org.song.member.controller.RequestJoin;
import org.song.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Lazy
@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator , PasswordValidator, MobileValidator {

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
        *  3. 비빌번호 확인 일치 여부
        *  4. 휴대 번호 형식 검증
        *
        * */
        //*  1. 이메일 중복 여부
        RequestJoin requestJoin = (RequestJoin) target;
        if(repository.existsByEmail(requestJoin.getEmail())){
            errors.rejectValue("email", "Duplicated");
        }
        //2. 비밀 번호 확인 일치 여부
        String password = requestJoin.getPassword();
        String confirmPassword = requestJoin.getConfirmPassword();
        if(!checkAlpha(password, false) || !checkNumber(password)){
            errors.rejectValue("password", "Complexity");
        }
        //*   3. 비빌번호 확인 일치 여부
        if(!password.equals(confirmPassword)){
            errors.rejectValue("confirmPassword", "Mismatch");
        }
        //. 휴대 번호 형식 검증
        String mobile = requestJoin.getMobile();
        if(!checkMobile(mobile)){
            errors.rejectValue("mobile", "Mobile");
        }
    }

}
