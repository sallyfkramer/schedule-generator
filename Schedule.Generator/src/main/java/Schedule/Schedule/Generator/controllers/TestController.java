package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.forms.PalindromeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("test")
public class TestController {


    @RequestMapping(value = "palindrome", method = RequestMethod.GET)
    public String enterValue(Model model) {
        model.addAttribute("title", "Is this a palindrome?");
        model.addAttribute("form", new PalindromeForm());
        return "test/palindrome";
    }

    @RequestMapping(value = "palindrome", method = RequestMethod.POST)
    public String testValue(Model model, @ModelAttribute @Valid PalindromeForm form, Errors errors){
        String input = form.getInput();
        int low = 0;
        int high = input.length()-1;
        String palindrome="unknown";

        while (high > low){
            if (Character.toLowerCase(input.charAt(low)) != Character.toLowerCase(input.charAt(high))){
                palindrome="Not a palindrome";
                break; }
            else palindrome="It's a palindrome!";
            low+=1;
            high-=1;
            }
        model.addAttribute("palindrome", palindrome);
        model.addAttribute("title", input);
        model.addAttribute(input);
        return "test/palindrome-result";

    }

}

