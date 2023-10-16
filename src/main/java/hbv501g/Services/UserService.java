package hbv501g.Services;

import hbv501g.Persistence.Entities.User;
import hbv501g.Persistence.Repositories.UserRepository;
import hbv501g.Utils.JwtUtils;
import hbv501g.Utils.PasswordUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

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

    public String authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "-no such user";
        }

        String hashed = PasswordUtils.hashPassword(password, user.getSalt());

        if (!hashed.equals(user.getPassword())) {
            return "-wrong password";
        }

        // remove sensitive data before returning it to the user.
        user.setSalt("");
        user.setPassword("");

        return jwtUtils.createToken(user);
    }
    public List<String> getAllUsernames(){
        Iterable<User> allusers = userRepository.findAll();
        List<String> retList = new ArrayList<>();
        for (User user : allusers) {
            retList.add(user.getUsername());
        }
        return retList;
    }
}
