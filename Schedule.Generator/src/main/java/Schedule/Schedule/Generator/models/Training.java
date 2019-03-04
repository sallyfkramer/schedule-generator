package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max = 25)
    private String name;

//    @ManyToMany(mappedBy =  "employee")
//    private List<Employee> employees;
//
//    @OneToMany
//    @JoinColumn(name = "post_id")
//    //private List<Cheese> cheeses = new ArrayList<>();
//    private List<Post> post = new ArrayList<>();

    public Training() {}




}
