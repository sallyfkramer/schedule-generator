package Schedule.Schedule.Generator.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Employee {
    //data fields//
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=45)
    private String lastName;

    @NotNull
    @Size(min=3, max=45)
    private String firstName;

    @ManyToMany
    private List<Training> trainings;


    @ManyToMany()
    private List<Shift> shifts;


    public Employee() { }


    //getters and setters//



    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addItem(Training item) {trainings.add(item);}

    public  List<Training> getTrainings() {return trainings; }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
