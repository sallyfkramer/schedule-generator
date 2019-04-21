package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.*;
import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.PostDao;
import Schedule.Schedule.Generator.models.data.ScheduleDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.forms.AddRosterForm;
import Schedule.Schedule.Generator.models.forms.EditPostsForm;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ShiftDao shiftDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PostDao postDao;

//    Index displays a list of schedules by name.

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("schedules", scheduleDao.findAll());
        model.addAttribute("title", "All Schedules");

        return "schedule/index";
    }

    //    Add new schedule and redirect to view for new schedule
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addScheduleForm(Model model) {

        model.addAttribute("title", "Add Schedule");
        model.addAttribute(new Schedule());
        model.addAttribute("shifts", shiftDao.findAll());

        return "schedule/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSchedulForm(@ModelAttribute @Valid Schedule newSchedule,
                                        Errors errors, Model model,
                                        @RequestParam int shiftId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Schedule");
            model.addAttribute("shifts", shiftDao.findAll());
            return "schedule/add";
        }
        Shift shift = shiftDao.findById(shiftId).orElse(null);
        newSchedule.setShift(shift);
        scheduleDao.save(newSchedule);
        return "redirect:/schedule/view/" + newSchedule.getId();
    }

    //    view for individual  schedule
    @RequestMapping(value = "view/{scheduleId}", method = RequestMethod.GET)
    public String viewSchedule(Model model, @PathVariable int scheduleId) {
        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);

        Iterable<Employee> allEmployees = schedule.getRoster();
        List<Employee> list = new ArrayList<Employee>();
        for (Employee employee : allEmployees) {
            list.add(employee);
        }
        Collections.sort(list, new Employee.EmployeeSortingComparator());

        model.addAttribute("title", schedule.getName());
        model.addAttribute("employees", list);
        model.addAttribute("scheduleId", schedule.getId());
        model.addAttribute("count", schedule.getRoster().size());
        model.addAttribute("shift", schedule.getShift());


        return "schedule/view";
    }


//    function to add or edit an existing roster to the schedule

    @RequestMapping(value = "add-roster/{scheduleId}", method = RequestMethod.GET)
    public String addRosterForm(Model model, @PathVariable int scheduleId) {

        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);
        Iterable<Employee> allEmployees = employeeDao.findAll();
        List<Employee> list = new ArrayList<Employee>();
        for (Employee employee : allEmployees) {
            list.add(employee);
        }
        Collections.sort(list, new Employee.EmployeeSortingComparator());

        if (schedule.getRoster().isEmpty())
            schedule.setRoster(schedule.getShift().getEmployees());

        model.addAttribute("title", "Edit roster");
        model.addAttribute("form", new AddRosterForm(schedule, list));
        return "schedule/add-roster";
    }

    @RequestMapping(value = "add-roster", method = RequestMethod.POST)
    public String processAddRosterForm(Model model, @ModelAttribute @Valid AddRosterForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "edit roster");
            model.addAttribute("form", form);
            return "employee/edit-shifts";
        }

        Schedule theSchedule = scheduleDao.findById(form.getScheduleId()).orElse(null);
        List<Employee> thisRoster = form.getSchedule().getRoster();


        theSchedule.setRoster(thisRoster);
        scheduleDao.save(theSchedule);

        return "redirect:/schedule/view/" + form.getScheduleId();

    }

    @RequestMapping(value = "edit-posts/{scheduleId}", method = RequestMethod.GET)
    public String editPostForm(Model model, @PathVariable int scheduleId) {

        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);
        Iterable<Post> allPosts = postDao.findAll();

        List<Post> list = new ArrayList<>();
        for (Post post : allPosts) {
            if (post.getPriority() == Priority.URGENT)
                list.add(post);
            else if (post.getPriority() == Priority.MANDATORY)
                list.add(post);
            else if (post.getPriority() == Priority.PREFERRED)
                list.add(post);
            else if (post.getPriority() == Priority.STRONG)
                list.add(post);
        }

        if (schedule.getPosts().isEmpty())
            schedule.setPosts(list);

        int rosterCount = schedule.getRoster().size();

        EditPostsForm editPostsForm = new EditPostsForm(allPosts, schedule, rosterCount);

        model.addAttribute("title", "Edit Post List");
        model.addAttribute("form", editPostsForm);

        return "schedule/edit-posts";
    }

    @RequestMapping(value = "edit-posts", method = RequestMethod.POST)
    public String processEditPostsForm(Model model, @ModelAttribute @Valid EditPostsForm form, Errors errors) {

        Iterable<Post> allPosts = postDao.findAll();
        form.getSchedule().setId(form.getScheduleId());
        EditPostsForm wholeForm = new EditPostsForm(allPosts, form.getSchedule(), form.getRosterCount());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Posts");
            model.addAttribute("form", wholeForm);
            return "schedule/edit-posts";
        }

        Schedule theSchedule = scheduleDao.findById(form.getScheduleId()).orElse(null);
        List<Post> thisPosts = form.getSchedule().getPosts();

        if (thisPosts.size() != form.getRosterCount()) {
            if (thisPosts.size() > form.getRosterCount()) {
                int difference = thisPosts.size() - form.getRosterCount();
                model.addAttribute("form", wholeForm);
                model.addAttribute("difference", difference);
                model.addAttribute("title", "Too many posts by " + difference);
                return "schedule/edit-posts";
            } else if (thisPosts.size() < form.getRosterCount()) {
                int difference = form.getRosterCount() - thisPosts.size();
                model.addAttribute("form", wholeForm);
                model.addAttribute("difference", difference);
                model.addAttribute("title", "Too few posts by " + difference);
                return "schedule/edit-posts";
            }
        }

        theSchedule.setPosts(thisPosts);
        scheduleDao.save(theSchedule);

        return "redirect:/schedule/view/" + theSchedule.getId();

    }

    @RequestMapping(value = "view-schedule/(scheduleId}", method = RequestMethod.GET)
    public String generateSchedule(Model model, @PathVariable int scheduleId) {
        Schedule schedule = scheduleDao.findById(scheduleId).orElse(null);
        int pri = 1;
        ArrayList<Post> rankedPosts = new ArrayList<>();

        while (pri < 8) {
            for (Post post : schedule.getPosts()) {
                if (post.getPriority().getNumber() == pri) {
                    rankedPosts.add(post);
                }
            }
            pri += 1;
        }
        model.addAttribute(rankedPosts);
        model.addAttribute("title", "Ranked Post List");
        return "redirect:/schedule/view-schedule/" + scheduleId;
    }
}