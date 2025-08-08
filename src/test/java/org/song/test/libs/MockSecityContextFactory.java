package org.song.test.libs;

import org.song.member.MemberInfo;
import org.song.member.entityes.Member;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.LocalDateTime;

public class MockSecityContextFactory implements WithSecurityContextFactory<Mock> {



    @Override
    public SecurityContext createSecurityContext(Mock annotation) {
    Member member = new Member();
    member.setSeq(annotation.seq());
    member.setEmail(annotation.email());
    member.setPassword(annotation.password());
    member.setName(annotation.name());
    member.setMobile(annotation.mobile());
    member.setAuthority(annotation.AUTHORITY());
    member.setCredentialChangedAt(LocalDateTime.now().plusMonths(1L));

        MemberInfo memberInfo= MemberInfo.builder().member(member).build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(memberInfo, null, memberInfo.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication); // 로그인 처리

        return context;
    }
}
