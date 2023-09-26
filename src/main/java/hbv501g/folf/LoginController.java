@Controller
public class LoginController {
    @GetMapping("../XMLFiles/login")
    public String showForm(){
        return "placeholder";
    }
}