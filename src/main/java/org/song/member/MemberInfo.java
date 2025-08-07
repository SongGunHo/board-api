package org.song.member;

import lombok.Builder;
import lombok.Data;
import org.song.member.entityes.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@Data
public class MemberInfo implements UserDetails{

    private String email;
    private String password;
    private Member member;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member == null ? null : List.of(new SimpleGrantedAuthority(member.getPassword()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member != null && member.getCredentialChangedAt().isAfter(LocalDateTime.now().minusDays(30L));
    }

    @Override
    public boolean isEnabled() {
        return member != null && member.getDeletedAt() == null;
    }

    @Override
    public String getPassword() {
        return member == null ? null : member.getPassword();
    }

    @Override
    public String getUsername() {
        return "";
    }
}
