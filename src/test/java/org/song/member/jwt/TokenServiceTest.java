package org.song.member.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.song.member.controller.RequestJoin;
import org.song.member.services.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"default", "test"})
public class TokenServiceTest {

    @Autowired
    private JoinService service;

    @Autowired
    private TokenService tokenService;

    @BeforeEach
    void init (){
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setPassword("123456");
        form.setConfirmPassword(form.getConfirmPassword());
        form.setName("사용자01");
        form.setMobile("01022223333");
        form.setTermsAgree(true);

        service.process(form);


    }
    @Test
    @DisplayName("토큰 발급")
    void jwtCreationTest(){
        String token = tokenService.create("user01@test.org");
        System.out.println(token);
    }
}
