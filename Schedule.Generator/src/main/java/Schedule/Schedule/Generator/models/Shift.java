package Schedule.Schedule.Generator.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Shift {
    @Id
    @GeneratedValue
    private int id;

//    @NotNull
//    @Size(min = 3, max = 35)
//    private String name;
//
//    @ManyToOne
//    private List<Employee> employeeList;
}
