package Schedule.Schedule.Generator.models.forms;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Schedule;

import javax.validation.constraints.NotNull;

public class AddRosterForm {

    @NotNull
    private int scheduleId;

    private Iterable<Employee> allEmployees;

    private Schedule schedule;

    public AddRosterForm() {}

    public AddRosterForm(Schedule schedule, Iterable<Employee> allEmployees){
        this.allEmployees=allEmployees;
        this.schedule=schedule;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Iterable<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(Iterable<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
