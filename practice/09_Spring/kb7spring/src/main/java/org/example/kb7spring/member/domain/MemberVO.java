package org.example.kb7spring.member.domain;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberVO {
    Long no;
    String username;
    String password;
    String email;
    Integer birthYear;
    Date regDate;
    Date updatedDate;
}
