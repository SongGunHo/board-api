package org.song.member.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.song.global.lib.Utis;
import org.song.member.MemberInfo;
import org.song.member.constants.Authority;
import org.song.member.entityes.Member;
import org.song.member.services.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Lazy
@EnableConfigurationProperties({JwtProperties.class})
public class TokenService {

    private final JwtProperties properties;
    private final MemberInfoService service;

    @Autowired
    private final Utis utis;
    private key key;


    public TokenService(JwtProperties properties, MemberInfoService infoService, Utis utis) {
        this.properties = properties;
        this.service = infoService;
        this.utis = utis;


        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * JWT 토큰 발급
     *
     * @param email
     * @return
     */
    public String create(String email) {

        MemberInfo userDetails = (MemberInfo)service.loadUserByUsername(email);
        Member member = userDetails.getMember();

        Date date = new Date(new Date().getTime() + properties.getValidTime() * 1000);

        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim("authority", member.getAuthority())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(date)
                .compact();
    }

    /**
     * 요청 처리
     * authorization: berarer 토큰
     * @param request
     * @return
     */
    public  Authentication authentication(ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        if(StringUtils.hasText(token)){
            return null;
        }
        token = token.substring(7);
        if(!StringUtils.hasText(token)){
            return null;
        }
        return authenticate(token);
    }
    /**
     * JWT 토큰으로 인증 처리(로그인 처리)
     *
     * 요청헤더
     *      Authorization: Bearer 토큰
     * @param token
     * @return
     */
    public Authentication authenticate(String token) {
        // 토큰 유효성 검사
        validate(token);
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getPayload();
        String email = claims.getSubject();
        Authority authority = Authority.valueOf((String)claims.get("authority"));

        MemberInfo userDetails =(MemberInfo) service.loadUserByUsername(email);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authority.name()));
        userDetails.getMember().setAuthority(authority);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 처리(로그인 처리)
        return authentication;
    }

    public void validate(String token){
        String errorCode = null;
        Exception error = null;
        try{
        Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getPayload();
        } catch (ExpiredJwtException e){// 토큰 만료
            errorCode = "JWT.expired";
            error = e;
        }catch (MalformedJwtException | SecurityException e) { // jwt 형식 오류
            errorCode = "JWT.malformed";
            error=e;
        }catch (UnsupportedJwtException e ){
            errorCode = "JWT.unsupported";
            error=e;
        }catch (Exception e){
            errorCode = "JWT.error";
            error=e;
        }
        if(StringUtils.hasText(errorCode)){
            throw
        }
        if(error != null){
            error.printStackTrace();
        }
    }

}
