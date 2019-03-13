package Schedule.Schedule.Generator.models.forms;

import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Shift;

import javax.validation.constraints.NotNull;

public class AddShiftForm {
    @NotNull
    private int employeeId;

    @NotNull
    private int shiftId;

    private Iterable<Shift> shifts;

    private Employee employee;

    public AddShiftForm() {}

    public AddShiftForm(Iterable<Shift> shifts, Employee employee) {
        this.shifts = shifts;
        this.employee = employee;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getShiftId() {
        return shiftId;
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
