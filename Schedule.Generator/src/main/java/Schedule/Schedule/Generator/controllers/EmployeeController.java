package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Training;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.TrainingDao;
import Schedule.Schedule.Generator.models.forms.AddTrainingForm;
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
        model.addAttribute("title", employee.getFirstName() +
                " " + employee.getLastName() );
        model.addAttribute("trainings", employee.getTrainings());
        model.addAttribute("employeeId", employee.getId());
        return "employee/view";
    }

    @RequestMapping(value = "add-training/{employeeId}", method = RequestMethod.GET)
    public String addTraining(Model model, @PathVariable int employeeId) {

        Employee employee = employeeDao.findById(employeeId).orElse(null);

        AddTrainingForm form = new AddTrainingForm(
                trainingDao.findAll(), employee);

        model.addAttribute("title", "Add training to " + employee.getFirstName() +
                " " + employee.getLastName()  );
        model.addAttribute("form", form);
        model.addAttribute("employee", employee);
        return "employee/add-training";
    }

    @RequestMapping(value = "add-training", method = RequestMethod.POST)
    public String addTraining(Model model, @ModelAttribute @Valid AddTrainingForm form, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "employee/add-training";
        }

        Training theTraining = form.getTraining();
        Employee theEmployee = employeeDao.findById(form.getEmployeeId()).orElse(null);
        theEmployee.addItem(theTraining);
        employeeDao.save(theEmployee);

        return "redirect:/employee/view/" + theEmployee.getId();
    }


}
