//package Schedule.Schedule.Generator.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Roster {
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @OneToOne
//    private Schedule schedule;
//
//    @ManyToMany(mappedBy = "rosters")
//    private List<Employee> employees;
//
//    public Roster() {}
//
//    public int getId() {
//        return id;
//    }
//
//    public Schedule getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(Schedule schedule) {
//        this.schedule = schedule;
//    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
////    TODO: list employees alphabetically Last Name, First Name
//}
