package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
    private Set<Employee> employees;


    public Schedule(){}

    public int getId() {
        return id;
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

    public Set<Employee> getRoster() {
        return employees;
    }

    public void setRoster(Set<Employee> roster) {
        this.employees = roster;
    }

    public void addEmployee(Employee employee) {employees.add(employee);}


}
