package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("shift")
public class ShiftController {

    @Autowired
    private ShiftDao shiftDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("shifts", shiftDao.findAll());
        model.addAttribute("title", "All Shifts");

        return "shift/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addShiftForm(Model model){

        model.addAttribute("title", "Add Shift");
        model.addAttribute(new Shift());

        return "shift/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddShiftForm(@ModelAttribute @Valid Shift newShift,
                                      Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Shift");
            return "shift/add";
        }

        shiftDao.save(newShift);
        return "redirect:";
    }
}

