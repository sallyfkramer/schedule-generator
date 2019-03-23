package Schedule.Schedule.Generator.models.forms;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Shift;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class EditShiftsForm {

    @NotNull
    private int employeeId;


    private Iterable<Shift> shifts;

    private Employee employee;

    public EditShiftsForm(){}

    public EditShiftsForm(Iterable<Shift> shifts, Employee employee){
        this.shifts = shifts;
        this.employee = employee;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    public Iterable<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Iterable<Shift> shifts) {
        this.shifts = shifts;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
