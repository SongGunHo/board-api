package org.song.member.repository;

import org.song.member.entityes.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
