package org.song.member.services;

import lombok.RequiredArgsConstructor;
import org.song.member.MemberInfo;
import org.song.member.entityes.Member;
import org.song.member.repository.MemberRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = repository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return MemberInfo.builder().member(member).build();
    }
}
