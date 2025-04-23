package org.zerock.sb2.member.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sb2.member.entity.MemberEntity;
import org.zerock.sb2.member.repository.MemberRepository;



@Transactional
@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void signup(String mid, String mpw, String email) {

        log.info(mid, mpw, email);

        MemberEntity member = MemberEntity.builder()
        .mid(mid)
        .mpw(mpw) // 실무라면 비밀번호 암호화!
        .email(email)
        .build();

    memberRepository.save(member);

    log.info("저장완료");
        
    }
}
