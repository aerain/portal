package kr.ac.jejunu.user.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn (name="userinfo_id")
    private User user;

    private String content;
}
