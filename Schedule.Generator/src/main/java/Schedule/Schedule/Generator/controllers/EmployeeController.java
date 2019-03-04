package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    //TODO set up Dao

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "")
    public String roster(Model model){

        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title","All Employees");

        return "employee/roster";
    }

    @RequestMapping(value = "/add")
    public String addEmployeeform(Model model) {
        model.addAttribute("title","Add Employee");
        model.addAttribute(new Employee());

        return "employee/add";
    }


}
