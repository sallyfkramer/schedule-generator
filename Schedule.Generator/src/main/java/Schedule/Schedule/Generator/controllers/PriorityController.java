package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("priority")
public class PriorityController {

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Post Priority Levels");
        model.addAttribute("priorities", Priority.values());

//TODO: make each name a link to a list of the posts at that priority level.//


        return "priority/index";
    }



}
