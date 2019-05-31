package kr.ac.jejunu.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="userinfo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 에 의존적이 전략
    private Integer id;
    private String name;
    private String password;
    @OneToMany
    @JoinColumn(name="userinfo_id")
    //정말 많이 쓰게돼
    @JsonIgnoreProperties(value={"user"})
    private List<Comment> comments;
}
