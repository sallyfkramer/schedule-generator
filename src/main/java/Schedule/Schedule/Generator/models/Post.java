package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

    @NotNull
    @Size(min = 2, max = 100)
    private String description;


    @ManyToOne
    private Training training;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Post(String name, String description){
        this.name= name;
        this.description= description;
    }

    public Post() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
