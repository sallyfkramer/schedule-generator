package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Priority;
import Schedule.Schedule.Generator.models.data.PriorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("priority")
public class PriorityController {

    @Autowired
    private PriorityDao priorityDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("priorities", priorityDao.findAll());
        model.addAttribute("title", "Post Priorities");

        return "priority/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPriorityForm(Model model){

        model.addAttribute("title", "Add Priority");
        model.addAttribute(new Priority());

        return "priority/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPriorityForm(@ModelAttribute @Valid Priority newPriority,
                                      Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Post Priorities");
            return "priority/add";
        }
        priorityDao.save(newPriority);
        return "redirect:";
    }
}
