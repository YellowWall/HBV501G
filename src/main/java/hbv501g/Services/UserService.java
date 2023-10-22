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

    /**
     * Function which registers a user to the database if no user with the same username exists
     * @param user parameters containing username and password, name of user is optional, but will be blank if none provided
     * @return the created user or null if no user created
     */
    public User createUser(User user) {
        User newUser = userRepository.findByUsername(user.getUsername());

        if (newUser != null) {
            return null;
        }

        String salt = PasswordUtils.generateSalt();
        //each user has their own private salt for encrytping password to ensure that the cracking of the encryption of one password
        //does not crack all
        String password = PasswordUtils.hashPassword(user.getPassword(), salt);
        newUser = new User(user.getUsername(), password, salt, user.getName());

        newUser = userRepository.save(newUser);
        System.out.println("USER: " + newUser);

        return newUser;
    }
    /**
     * returns an authentication token showing that user has been succesfully logged in
     * this token is used for authorization of various actions
     * @param username 
     * @param password
     * @return authentication token
     */
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

    /**
     * Finds user object by username
     * @param username user's username
     * @return user object
     */
    public User getUser(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        user.setSalt("");
        user.setPassword("");
        return user;

    }

    /**
     * Skilar öllum nöfnum notenda
     * @return listi af nöfnum notenda
     */
    public List<String> getAllUsernames() {
        List<User> users = userRepository.findAll();
        List<String> names = new ArrayList<>();
        for (User i : users){
            names.add(i.getUsername());
        }
        return names;
    }
}
