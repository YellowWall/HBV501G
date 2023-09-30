package hbv501g.Services;

import hbv501g.Persistence.Entities.User;
import hbv501g.Persistence.Repositories.UserRepository;
import hbv501g.Utils.PasswordUtils;
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

        String salt = PasswordUtils.generateSalt();
        String password = PasswordUtils.hashPassword(user.getPassword(), salt);
        newUser = new User(user.getUsername(), password, salt, user.getName());

        newUser = userRepository.save(newUser);
        System.out.println("USER: " + newUser);

        return newUser;
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        String hashed = PasswordUtils.hashPassword(password, user.getSalt());

        if (!hashed.equals(user.getPassword())) {
            return null;
        }

        // remove sensitive data before returning it to the user.
        user.setSalt("");
        user.setPassword("");

        return user;
    }
}
