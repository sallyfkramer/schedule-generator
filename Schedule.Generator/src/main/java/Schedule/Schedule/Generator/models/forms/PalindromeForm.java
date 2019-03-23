package Schedule.Schedule.Generator.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PalindromeForm {

    @NotNull
    @Size(min = 1, max=200)
    public String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
