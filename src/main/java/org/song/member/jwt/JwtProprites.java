package org.song.member.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "member.jwt")
public class JwtProprites {
    private String secret;
    private int validTime;
}
