package org.song.member.jwt;

import org.junit.jupiter.api.BeforeEach;
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

    }
}
