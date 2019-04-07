package Schedule.Schedule.Generator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    private Set<Training> trainings;

    @ManyToMany()
    private Set<Shift> shifts;

    @ManyToMany
    private List<Schedule> schedules;

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

    public void addShift(Shift shift) {shifts.add(shift);}

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public static class EmployeeSortingComparator implements Comparator<Employee> {

        public int compare(Employee employee1, Employee employee2) {

            int LastNameCompare = employee1.getLastName().compareTo(employee2.getLastName());
            int FirstNameCompare = employee1.getFirstName().compareTo(employee2.getFirstName());

            if (LastNameCompare == 0) {
                return ((FirstNameCompare == 0) ? LastNameCompare : FirstNameCompare);
            } else {
                return LastNameCompare;
            }
        }
    }


}
