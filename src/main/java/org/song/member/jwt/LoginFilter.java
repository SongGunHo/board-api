package org.song.member.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.song.global.excepotion.UnAuthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFilter extends GenericFilterBean {
    private final TokenService service;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {
        service.authentication(request);
    } catch (UnAuthorizedException e) {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

        e.printStackTrace();
    }
        chain.doFilter(request, response);
}
    }
