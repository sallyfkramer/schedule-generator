package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @RequestMapping(value = "view/{shiftId}", method = RequestMethod.GET)
    public String viewShift(Model model, @PathVariable int shiftId) {
        Shift shift = shiftDao.findById(shiftId).orElse(null);
        Iterable<Employee> employees= shift.getEmployees();
        List<Employee> list = new ArrayList<Employee>();
        for (Employee employee:employees){
            list.add(employee);
        }
        Collections.sort(list, new Employee.EmployeeSortingComparator());

        model.addAttribute("title", shift.getName());
        model.addAttribute("employees", list);
        model.addAttribute("shiftId", shift.getId());
        model.addAttribute("count", shift.getEmployees().size() );

        return "shift/view";
    }
}

