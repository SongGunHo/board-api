package org.song.test.libs;

import org.song.member.constants.Authority;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MockSecityContextFactory.class)
public @interface Mock {
    long seq() default 1L;
    String email() default  "testUser@test.org";
    String password() default  "1234567";
    String name() default  "사용자";
    String mobile() default  "010--2222-3333";
    Authority AUTHORITY() default  Authority.MEMBER;
}
