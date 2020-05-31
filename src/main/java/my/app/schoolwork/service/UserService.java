package my.app.schoolwork.service;

import my.app.schoolwork.entity.User;
import my.app.schoolwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean login(String username, String password){
        User user = userRepository.findByName(username);
        return user.isTruePassword(password);
    }

    public User getUserByName(String username){
        return userRepository.findByName(username);
    }
}
