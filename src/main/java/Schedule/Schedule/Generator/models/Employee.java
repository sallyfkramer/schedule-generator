package Schedule.Schedule.Generator.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

//    @ManyToMany(mappedBy = "employees")
//    private List<Training> trainings;

    public Employee(String lastName, String firstName){
        this.firstName= firstName;
        this.lastName = lastName;
    }

    public Employee() { }


    //getters and setters//


//    public List<Training> getTrainings() {
//        return trainings;
//    }
//
//    public void setTrainings(List<Training> trainings) {
//        this.trainings = trainings;
//    }

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
}
