package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Persistence.Entities.User;
import hbv501g.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public User getUser(){
        return new User("ivara", "asdf1234", "xxxx","Ívar Árnason");
    }

    @PostMapping("/signup")
    public JsonResponse<User> signupUser(@RequestBody User user) {
        User newUser = userService.createUser(user);

        if (newUser == null) {
             return new JsonResponse<User>(204, "Could not create user!", null);
        }

        return new JsonResponse<User>(201, "User created", newUser);
    }
}
