package org.zerock.sb2.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.sb2.member.entity.MemberEntity;
import org.zerock.sb2.member.repository.MemberRepository;
import org.zerock.sb2.member.service.MemberService;
import org.zerock.sb2.util.JWTException;
import org.zerock.sb2.util.JWTUtil;

import java.util.Map;
import java.util.Optional;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SocialController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final JWTUtil jwtUtil;

    @PostMapping("/api/v1/member/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> param) {

        log.info("login 시도----------------------------");

        String mid = param.get("mid");
        String mpw = param.get("mpw");
        
        Optional<MemberEntity> result = memberRepository.findById(mid);
       
        if(result.isEmpty()) {
            // 아이디 없음
            return ResponseEntity.ok("아이디없어");
        }
       
        MemberEntity member = result.get();

        // 비밀번호 일치 검사 (실무는 인코더, 지금은 plain 비교)
        if(!member.getMpw().equals(mpw)) {
            // 아이디 없음
            return ResponseEntity.ok("비밀번호 불일치");
        }
       
    
        // 로그인 성공 → 토큰 발급
        String accessToken = jwtUtil.createToken(Map.of("mid", mid), 5);
        String refreshToken = jwtUtil.createToken(Map.of("mid", mid), 10);
    
        String[] resultArr = new String[]{accessToken, refreshToken};
        return ResponseEntity.ok(resultArr);
    }

    @RequestMapping("/api/v1/member/refresh")
    public ResponseEntity<String[]> refresh (
            @RequestHeader("Authorization") String accessTokenStr,
            @RequestParam("refreshToken") String refreshToken) {

        String accessToken = accessTokenStr.substring(7);
        String mid = "user00";

        try {
            jwtUtil.validateToken(refreshToken);
        }catch(Exception e){
            log.error("refresh token validation failed", e);

            throw new JWTException(e.getMessage());
        }

        String newAccessToken = jwtUtil.createToken(Map.of("mid",mid), 5);
        String newRefreshToken = jwtUtil.createToken(Map.of("mid",mid), 10); //60*24*7

        String[] result = new String[]{newAccessToken, newRefreshToken};

        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/v1/member/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> param) {

    log.info("==== 회원가입 시도됨 ====");

    String mid = param.get("mid");
    String email = param.get("email");
    String mpw = param.get("mpw");
    // 회원가입 로직...
    memberService.signup(mid, mpw, email);
   
    log.info("====회원가입성공===");


    return ResponseEntity.status(HttpStatus.CREATED).build();
}


}
