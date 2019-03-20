package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.forms.EditShiftsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ShiftDao shiftDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String test(Model model) {

        model.addAttribute("employee", employeeDao.findById(26).orElse(null));
        model.addAttribute("title", "Edit Schedule");
        model.addAttribute("allShifts", shiftDao.findAll());
        model.addAttribute( new EditShiftsForm());

        return "test/test";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String testPost(@ModelAttribute @Valid EditShiftsForm newForm,
                           Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit");
            model.addAttribute("allShifts", shiftDao.findAll());
            return "test/test";
        }

        Employee theEmployee = employeeDao.findById(26).orElse(null);
        for (Shift shift : newForm.getTheseShifts()){
            theEmployee.addShift(shift);
            employeeDao.save(theEmployee); }

        return "redirect:/employee/view/26";
    }

}
