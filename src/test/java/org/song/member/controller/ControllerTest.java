package org.song.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @DisplayName("회원 가입 컨트로러 테스트")
    void test() throws Exception{
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setPassword("123456");
        form.setConfirmPassword(form.getConfirmPassword());
        form.setName("사용자01");
        form.setMobile("01022223333");
        form.setTermsAgree(true);
        mvc.perform(post("/api/vi/member").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());

    }

}
