package org.song.member.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.song.member.constants.Authority;
import org.song.member.controller.RequestJoin;
import org.song.member.entityes.Member;
import org.song.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@Lazy
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder; // 비밀 번호 암호화
    private final ModelMapper mapper;

    public void process(RequestJoin form){
        Member member = mapper.map(form, Member.class);

        String password = form.getPassword();
        if(StringUtils.hasText(password)){
            member.setPassword(encoder.encode(password));
        }
        member.setCreatedAt(LocalDateTime.now());
        member.setAuthority(Authority.MEMBER);

        repository.saveAndFlush(member);
    }

}
