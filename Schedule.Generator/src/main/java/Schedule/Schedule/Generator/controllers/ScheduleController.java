package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Schedule;
import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.data.ScheduleDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ShiftDao shiftDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("schedules", scheduleDao.findAll());
        model.addAttribute("title", "All Schedules");

        return "schedule/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addScheduleForm(Model model){

        model.addAttribute("title", "Add Schedule");
        model.addAttribute(new Schedule());
        model.addAttribute("shifts", shiftDao.findAll());

        return "schedule/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSchedulForm(@ModelAttribute @Valid Schedule newSchedule,
                                        Errors errors, Model model,
                                        @RequestParam int shiftId){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Schedule");
            model.addAttribute("shifts", shiftDao.findAll());
            return "schedule/add";
        }
        Shift shift = shiftDao.findById(shiftId).orElse(null);
        newSchedule.setShift(shift);
        scheduleDao.save(newSchedule);
        return "redirect:";
    }

//    @RequestMapping(value = "add-roster", method = RequestMethod.GET)
//    public String addRosterForm(Model model){
//        mo
//    }
}
