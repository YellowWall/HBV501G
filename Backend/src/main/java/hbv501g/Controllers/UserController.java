package hbv501g.Controllers;

import hbv501g.Classes.JsonResponse;
import hbv501g.Classes.UserPassCreds;
import hbv501g.Persistence.Entities.User;
import hbv501g.Services.UserService;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.OPTIONS, path = "/")
    public JsonResponse<String> optionsRequest()
    {
        return new JsonResponse<String>(true, "Allowed", "");
    }

    /**
     * return full user object when given partial User object, purely for debugging. Will be redesigned or completely eliminated before launch
     * @param userName User object containing at least a username
     * @return full User object for appropriate username
     */
    @PostMapping("/user")
    public JsonResponse<User> getUser(@RequestBody User userName){
        //System.out.println(userName.getUsername());
        if(userName == null || userName.getUsername() == null){
            return null;
        }
        User returnUser = userService.getUser(userName.getUsername());
        return new JsonResponse<User>(true,"User info",returnUser);
    }

    /**
     * Function for registering new users for folf tracker
     * @param user partial User object containing username, password and optionally real name
     * @return jsonresponse with new User depending on whether user could be created or not
     */
    @PostMapping("/signup")
    public JsonResponse<User> signupUser(@RequestBody User user) {
        User newUser = userService.createUser(user);

        if (newUser == null) {
             return new JsonResponse<User>(false, "Could not create user!", null);
        }

        return new JsonResponse<User>(true, "User created", newUser);
    }
    /**
     * function for authenticating user
     * @param creds username and password
     * @return  jsonresponse containing token of user
     */
    @PostMapping("/login")
    public JsonResponse<String> loginUser(@RequestBody UserPassCreds creds) {
        String jwtToken = userService.authenticateUser(creds.getUsername(), creds.getPassword());

        if (jwtToken.charAt(0) == '-') {
            return new JsonResponse<String>(false, "Not Authenticated!", jwtToken);
        }

        return new JsonResponse<String>(true, "Authenticated", jwtToken);
    }

    /**
     * Skilar nöfnum allra notenda sem finnast
     * @return JSON object með nöfnum allra sem finnast
     */
    @GetMapping("/users")
    public JsonResponse<String> allUsers(){
        List<String> list = userService.getAllUsernames();
        if(list == null){
            return new JsonResponse<String>(false, "no names", null);
                }
        return new JsonResponse<String>(true, "all usernames", list.toString());
    }
}
