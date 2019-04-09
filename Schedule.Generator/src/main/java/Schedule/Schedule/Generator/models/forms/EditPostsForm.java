package Schedule.Schedule.Generator.models.forms;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Post;
import Schedule.Schedule.Generator.models.Schedule;


import javax.validation.constraints.NotNull;

public class EditPostsForm {

    @NotNull
    private int scheduleId;

    private Iterable<Post> allPosts;

    private Schedule schedule;

    private int rosterCount;

    public EditPostsForm(){}

    public EditPostsForm(Iterable<Post> allPosts, Schedule schedule,int rosterCount){
        this.schedule = schedule;
        this.allPosts = allPosts;
        this.rosterCount = rosterCount;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Iterable<Post> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(Iterable<Post> allPosts) {
        this.allPosts = allPosts;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getRosterCount() {
        return rosterCount;
    }

    public void setRosterCount(int rosterCount) {
        this.rosterCount = rosterCount;
    }
}
