package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.Post;
import Schedule.Schedule.Generator.models.Priority;
import Schedule.Schedule.Generator.models.Training;
import Schedule.Schedule.Generator.models.data.PostDao;
import Schedule.Schedule.Generator.models.data.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import  java.util.List;

@Controller
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private TrainingDao trainingDao;

    @RequestMapping(value = "")
    public String postList(Model model){

        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Post List");

        return "post/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPostForm(Model model){

        model.addAttribute("title", "Add new post");
        model.addAttribute(new Post());
        model.addAttribute("trainings", trainingDao.findAll() );
        model.addAttribute("priorities", Priority.values());
        return "post/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPostForm(@ModelAttribute @Valid Post newPost,
                                     Errors errors, Model model,
                                     @RequestParam int trainingId){

        if (errors.hasErrors()){
            model.addAttribute("title", "Add Employee");
            model.addAttribute("trainings", trainingDao.findAll());
            model.addAttribute("priorities", Priority.values());
            return "post/add";
        }

        Training train = trainingDao.findById(trainingId).orElse(null);
        newPost.setTraining(train);
        postDao.save(newPost);
        return "redirect:";
    }

//TODO: add an edit page//

//TODO: add a remove page//

}
