package vit.projects.hudeem.services;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @Test
    @Transactional
    void getAll() {
        System.out.println("обращаемся к базе, еще не выполнено");
        List<UserEntity> all = userService.getAll();
        System.out.println("обращаемся к базе, уже выполнено");
    }


}