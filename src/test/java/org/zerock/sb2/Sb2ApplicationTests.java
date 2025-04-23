package org.zerock.sb2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.sb2.member.entity.MemberEntity;
import org.zerock.sb2.member.repository.MemberRepository;

@SpringBootTest
class Sb2ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;


	@Test
	public void testInsert() {
		MemberEntity memberEntity = MemberEntity.builder()
		.mid("user00")
		.mpw("0000")
		.email("yjyj0123@naver.com")
		.build();

		memberRepository.save(memberEntity);

		System.out.println("완료");
	}

}
