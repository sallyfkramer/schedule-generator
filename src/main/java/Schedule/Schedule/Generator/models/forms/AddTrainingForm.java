package Schedule.Schedule.Generator.models.forms;


import Schedule.Schedule.Generator.models.Employee;
import Schedule.Schedule.Generator.models.Training;

import javax.validation.constraints.NotNull;

public class AddTrainingForm {

    @NotNull
    private int trainingId;

    @NotNull
    private int employeeId;

    private Iterable<Training> trainings;

    private Employee employee;

    private Training training;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public AddTrainingForm() {
    }

    public AddTrainingForm(Iterable<Training> trainings, Employee employee) {
        this.trainings = trainings;
        this.employee = employee;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public Iterable<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Iterable<Training> trainings) {
        this.trainings = trainings;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}