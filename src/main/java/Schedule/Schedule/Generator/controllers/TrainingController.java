package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Training;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("training")
public class TrainingController {

    @Autowired
    private TrainingDao trainingDao;

//    @Autowired
//    private EmployeeDao employeeDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("trainings", trainingDao.findAll());
        model.addAttribute("title", "Training Types");

        return "training/index";
    }

    @RequestMapping(value = "add", method =  RequestMethod.GET)
    public String addTrainingForm(Model model){

        model.addAttribute("title", "Add Training");
        model.addAttribute(new Training());

        return "training/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTrainingForm(@ModelAttribute @Valid Training newTraining,
                                         Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Training");
            model.addAttribute("trainings", trainingDao.findAll());
            return "training/add";
        }

        trainingDao.save(newTraining);
        return "redirect:";
    }

//TODO make Remove page


}
