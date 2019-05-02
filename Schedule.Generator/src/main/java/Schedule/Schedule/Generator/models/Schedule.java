package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 60)
    private String name;

    @ManyToOne
    private Shift shift;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Post> posts;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<Pair> pairs= new ArrayList<>();

    public Schedule(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public List<Employee> getRoster() {
        return employees;
    }

    public void setRoster(List<Employee> roster) {
        this.employees = roster;
    }

    public void addEmployee(Employee employee) {employees.add(employee);}

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }
}
