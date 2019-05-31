package kr.ac.jejunu.user.controller;

import kr.ac.jejunu.user.model.User;
import kr.ac.jejunu.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @GetMapping("/list")
    public List<User> list() {
        List<User> all = repository.findAll();
        all.forEach(user -> {
            user.getComments().size();
        });
        return all;
    }

    @GetMapping("/listByName/{name}")
    public List<User> listByName(@PathVariable("name") String name) {
        return repository.findAllByName(name);
    }
}
