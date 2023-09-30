package hbv501g.Services;

import hbv501g.Persistence.Entities.User;
import hbv501g.Persistence.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User newUser = userRepository.findByUsername(user.getUsername());

        if (newUser != null) {
            return null;
        }

        newUser = userRepository.save(user);
        System.out.println("USER: " + newUser);

        return newUser;
    }
}
