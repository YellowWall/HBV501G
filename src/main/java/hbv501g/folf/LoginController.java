package hbv501g.folf;

@Controller
public class LoginController {
    @GetMapping("../XMLFiles/login")
    public String showForm(){
        return "placeholder";
    }
}