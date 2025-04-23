package org.zerock.sb2.member.controller.advice;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.sb2.util.JWTExcepion;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class JWTControllerAdivce {

   @ExceptionHandler(JWTExcepion.class)
   public ResponseEntity<Map<String, Object>> handelJMTxeption(JWTExcepion e){


        log.error("==============error==========");
        log.error("==============error==========");
        
        log.error("");

        ResponseEntity<Map<String, Object>> result
        = ResponseEntity.status(401).body((Map.of("error", e. getMessage())));

        return result;


    
   }

    
}
