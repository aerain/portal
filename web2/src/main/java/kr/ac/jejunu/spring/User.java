package kr.ac.jejunu.spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 오브젝ㅌ 생성위한 기본 패너
public class User {
    private Integer id;
    private String name;
    private String password;
}
