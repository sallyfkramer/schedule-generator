package Schedule.Schedule.Generator.models.forms;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Post;
import Schedule.Schedule.Generator.models.Shift;


import javax.validation.constraints.NotNull;

public class EditPostsForm {

    @NotNull
    private int employeeId;

    private Iterable<Post> allPosts;

    private Employee employee;

    public EditPostsForm(){}

    public EditPostsForm(Iterable<Post> posts, Employee employee){
        this.allPosts = allPosts;
        this.employee = employee;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Iterable<Post> getAllPosts() {
        return allPosts;
    }

    public void setAllPosts(Iterable<Post> allPosts) {
        this.allPosts = allPosts;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
