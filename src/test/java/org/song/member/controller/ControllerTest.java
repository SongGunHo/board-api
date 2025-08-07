package org.song.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.song.member.services.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.reflect.Array.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({""})
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private JoinService service;

    void init() throws Exception{
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setPassword("123456");
        form.setConfirmPassword(form.getConfirmPassword());
        form.setName("사용자01");
        form.setMobile("01022223333");
        form.setTermsAgree(true);

        RequestToken form2 = new RequestToken();
        form.setEmail("user01@test.org");
        form.setPassword("12345678");
        String body = om.writeValueAsString(form);

    }

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

        String body = om.writeValueAsString(form);

        mvc.perform(post("/api/vi/member").contentType(MediaType.APPLICATION_JSON).content(body)).andDo(print()).andExpect(status().isCreated());

    }

    @Test
    @DisplayName("이메일 비밀 번호 토큰 발급 테스트 ")
    void test2() throws Exception {
        RequestToken form = new RequestToken();
        form.setEmail("user01@test.org");
        form.setPassword("12345678");
        String body = om.writeValueAsString(form);

        mvc.perform(post("/api/vi/member/token").contentType(MediaType.APPLICATION_JSON).content(body)).andDo(print()).andReturn().getResponse().getContentAsString();
        // 회원 전용 관리자 전용 접근테스트
        mvc.perform(get("/api/v1/member/test2")
                        .header("Authorization", "Bearer " + token))
                .andDo(print());
    }

}
