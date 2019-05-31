package kr.ac.jejunu.user.repository;

import kr.ac.jejunu.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findAllByName(String name);

    // distinct는 유저만 유니크하게 보여주고 싶을때
    @Query("SELECT distinct u from Comment c join c.user u where c.content like concat(:content, '%')") //hibernate Query Language
    public List<User> findAllByContent(@Param("content") String content);
}
