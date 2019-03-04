package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

//    @ManyToOne
//    private Training trainings;
//    //@JoinColumn(name = "training_id")
//    //private List<Training> trainings = new ArrayList<>();

    public Post() {}



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Training> getTrainings() {
//        return trainings;
//    }
//
//    public void setTrainings(List<Training> trainings) {
//        this.trainings = trainings;
//    }
}
