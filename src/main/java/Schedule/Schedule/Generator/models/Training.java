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

    @NotNull
    @Size(min=3, max=50)
    private String description;

    public Training(String name, String description){
        this.name= name;
        this.description= description;
    }

    @OneToMany
    @JoinColumn(name= "training_id")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy =  "trainings")
    private List<Employee> employees;

    public Training() {}

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


}
