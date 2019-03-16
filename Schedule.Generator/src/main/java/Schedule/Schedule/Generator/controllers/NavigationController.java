package Schedule.Schedule.Generator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("set-up")
public class NavigationController {

    @RequestMapping(value = "")
    public String setUp(Model model){
        model.addAttribute("title","Set-Up");

        return "navigation/set-up";
    }
}
