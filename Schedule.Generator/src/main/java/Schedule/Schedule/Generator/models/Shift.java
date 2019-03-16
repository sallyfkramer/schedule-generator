package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shift {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max = 25)
    private String name;

    @NotNull
    @Size(min=3, max=50)
    private String description;

    @ManyToMany(mappedBy = "shifts")
    private List<Employee> employees;

    @OneToMany
    @JoinColumn(name= "shift_id")
    private List<Schedule> schedules = new ArrayList<>();


    public Shift(String name, String description){
        this.name= name;
        this.description= description;
    }

    public Shift(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
