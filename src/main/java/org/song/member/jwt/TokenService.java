package org.song.member.jwt;

import io.jsonwebtoken.Jwt;
import org.song.member.MemberInfo;
import org.song.member.entityes.Member;
import org.song.member.services.MemberInfoService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
@Lazy
@EnableConfigurationProperties({JwtProperties.class})
public class TokenService {

    private final JwtProperties properties;
    private final MemberInfoService service;
    private final key key;


    public TokenService(JwtProperties properties , MemberInfoService service){
        this.properties = properties;
        this.service = service;

        byte[] keyBytes = Base64.Decoder.decoder(properties.getSecret());
        this.key = Key.hmacChaKeyFor(keyBytes);
    }

    /**
     * JWT 토큰 발급
     *
     */
     public String create(String email){

        MemberInfo memberInfo=(MemberInfo) service.loadUserByUsername(email);

         Member member = memberInfo.getMember();


         return null;
     }

    /**
     * JWT 토킁으로 인증 처리 (로그인 처리)
     * 요청 해더
     *  Authorization:  Bearer : 토큰
     * @param token
     * @return
     */

     public Authentication authentication(String token){
          return null;
     }

}
