package org.zerock.sb2.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerock.sb2.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    // 필요시 커스텀 메서드 추가
}
