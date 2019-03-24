package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Schedule;
import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.ScheduleDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.forms.AddRosterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ShiftDao shiftDao;

    @Autowired
    private EmployeeDao employeeDao;

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

//    TODO: add schedule view
    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewSchedule(Model model, @PathVariable int scheduleId) {
        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);

        model.addAttribute("title", schedule.getName());
        model.addAttribute("employees", schedule.getRoster());
        model.addAttribute("scheduleId", schedule.getId());
        model.addAttribute("count", schedule.getRoster().size());
        model.addAttribute("shift", schedule.getShift());


        return "schedule/view";
    }


//    TODO: add post for add-roster

    @RequestMapping(value = "add-roster/{scheduleId}", method = RequestMethod.GET)
    public String addRosterForm(Model model, @PathVariable int scheduleId){

        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);
        Iterable<Employee> allEmployees = employeeDao.findAll();
        schedule.setRoster(schedule.getShift().getEmployees());

        model.addAttribute("title", "Edit roster");
        model.addAttribute("form", new AddRosterForm(schedule, allEmployees));
        return "schedule/add-roster";
    }
}
