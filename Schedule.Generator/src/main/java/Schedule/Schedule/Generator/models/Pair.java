package Schedule.Schedule.Generator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pair {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Schedule schedule;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
