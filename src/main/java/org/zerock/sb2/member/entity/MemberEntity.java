package org.zerock.sb2.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter 
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class MemberEntity {

    @Id
    private String mid; // ← 실무에선 id라고 써도 무방, mid로 쓰면 DB 컬럼과 맞아 떨어짐

    private String email;

    private String mpw;

    private String social;

    private boolean del;


    
}
