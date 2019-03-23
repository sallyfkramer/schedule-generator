package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Shift;
import Schedule.Schedule.Generator.models.Training;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.data.TrainingDao;
import Schedule.Schedule.Generator.models.forms.AddTrainingForm;
import Schedule.Schedule.Generator.models.forms.EditShiftsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private TrainingDao trainingDao;

    @Autowired
    private ShiftDao shiftDao;

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
        model.addAttribute("shifts", shiftDao.findAll());

        return "employee/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEmployeeForm(@ModelAttribute @Valid Employee newEmployee,
                                         Errors errors, Model model ) {
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Employee");
            model.addAttribute("shifts", shiftDao.findAll());
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
        model.addAttribute("shifts", employee.getShifts());
        return "employee/view";
    }
    // TODO: make it so that the shifts appear in order.


    @RequestMapping(value = "add-training/{employeeId}", method = RequestMethod.GET)
    public String addTraining(Model model, @PathVariable int employeeId) {

        Employee employee = employeeDao.findById(employeeId).orElse(null);

        AddTrainingForm form = new AddTrainingForm(
                trainingDao.findAll(), employee);

        model.addAttribute("title", "Add training to " + employee.getFirstName() +
                " " + employee.getLastName()  );
        model.addAttribute("form", form);
//        model.addAttribute("employee", employee);
        return "employee/add-training";
    }

    @RequestMapping(value = "add-training", method = RequestMethod.POST)
    public String addTraining(Model model, @ModelAttribute @Valid AddTrainingForm form, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "employee/add-training";
        }

        Training theTraining = trainingDao.findById(form.getTrainingId()).orElse(null);
        Employee theEmployee = employeeDao.findById(form.getEmployeeId()).orElse(null);
        theEmployee.addItem(theTraining);
        employeeDao.save(theEmployee);

        return "redirect:/employee/view/" + theEmployee.getId();
    }

//TODO: add edit schedule page//

    @RequestMapping(value = "edit-shifts/{employeeId}", method = RequestMethod.GET)
    public String editSchedule(Model model, @PathVariable int employeeId) {
        Employee employee = employeeDao.findById(employeeId).orElse(null);
        Iterable<Shift> allShifts = shiftDao.findAll();
        EditShiftsForm form = new EditShiftsForm(allShifts,employee);

        model.addAttribute("title", "Edit schedule for " + employee.getFirstName() +
                " " + employee.getLastName()  );
        model.addAttribute("form", form);

        return "employee/edit-shifts";
    }

    @RequestMapping(value = "edit-shifts", method = RequestMethod.POST)
    public String processEditScheduleForm(Model model,@ModelAttribute @Valid EditShiftsForm form, Errors errors) {

//        if (errors.hasErrors()) {
//            model.addAttribute("form", form);
//            return "employee/edit-shifts";
//        }
//        Employee theEmployee = employeeDao.findById(form.getEmployeeId()).orElse(null);
//        Set<Integer> theseShifts = form.getTheseShifts();
//        for (Shift shift : shiftDao.findAll()){
//            theEmployee.getShifts().remove(shift);
//        }
//        for (int id : theseShifts){
//        theEmployee.addShift(shiftDao.findById(id).orElse(null));
//        employeeDao.save(theEmployee);}
////        return "redirect:/employee/roster";
//        return "redirect:/employee/view/" + theEmployee.getId();
        model.addAttribute("form", form);
        return "/test/view";
        }


    //    public void removeShift(Shift shift){employeeDao.findById().delete(shift);}
//    group.getUsers().remove(user);
//    Boolean exists = employeeDao.findById(employeeId).isPresent();
//    Boolean x = Boolean.TRUE;
//        model.addAttribute("x", x);

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveEmployeeForm(Model model) {

        model.addAttribute("employees", employeeDao.findAll());
        model.addAttribute("title", "Remove Employee");
        return "employee/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveEmployeeForm(@RequestParam int[] employeeIds) {

        for (int employeeId : employeeIds) {
            Employee employee=employeeDao.findById(employeeId).orElse(null);
            employeeDao.delete(employee);
        }

        return "redirect:";
    }
}
