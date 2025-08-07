package org.song.member.entityes;

import jakarta.persistence.*;
import lombok.Data;
import org.song.global.enties.BaseEntity;
import org.song.member.constants.Authority;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
@Table(indexes = {
        @Index(name="idx_member_created_at", columnList = "createdAt DESC"),
        @Index(name="idx_member_name", columnList = "name"),
        @Index(name="idx_member_mobile", columnList = "mobile"),
})
public class Member extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 75, unique = true, nullable = false)
    private String email;

    @Column(length = 65)
    private String password;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.MEMBER;


    private boolean termsAgree;

    private boolean locked; // 계정 중지 상태인지

    private LocalDateTime expired; // 계정 만료 일자, null이면 만료

}