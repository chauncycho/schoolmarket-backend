package my.app.schoolwork;

import my.app.schoolwork.entity.User;
import my.app.schoolwork.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class SchoolworkApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void demo1(){
        User user = new User();
        user.setName("chauncy");
        user.setPassword("123456");
        user.setManager(true);
        userRepository.save(user);
    }
}
