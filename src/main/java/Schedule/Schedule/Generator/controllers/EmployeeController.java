package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    TrainingDao trainingDao;

    @RequestMapping(value = "")
    public String roster(Model model){

        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title","All Employees");

        return "employee/roster";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addEmployeeform(Model model) {

        model.addAttribute("title","Add Employee");
        model.addAttribute(new Employee());

        return "employee/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEmployeeForm(@ModelAttribute @Valid Employee newEmployee,
                                         Errors errors, Model model ) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Employee");
            return "employee/add";
        }

        employeeDao.save(newEmployee);
        return "redirect:";
    }

    @RequestMapping(value = "view/{employeeId}", method = RequestMethod.GET)
    public String viewEmployee(Model model, @PathVariable int employeeId){
        Employee employee = employeeDao.findById(employeeId).orElse(null);
        model.addAttribute("title", employee.getLastName());
        return "employee/view";
    }


//    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
//    public String viewMenu(Model model, @PathVariable int menuId) {
//        Menu menu = menuDao.findOne(menuId);
//        model.addAttribute("title", menu.getName());
//        model.addAttribute("cheeses", menu.getCheeses());
//        model.addAttribute("menuId", menu.getId());
//
//        return "menu/view";

}
