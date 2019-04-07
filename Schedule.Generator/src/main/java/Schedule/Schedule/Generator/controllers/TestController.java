package Schedule.Schedule.Generator.controllers;

import Schedule.Schedule.Generator.models.data.EmployeeDao;
import Schedule.Schedule.Generator.models.data.ShiftDao;
import Schedule.Schedule.Generator.models.forms.PalindromeForm;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


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

    @RequestMapping(value = "fizzbuzz", method = RequestMethod.GET)
    public String testFizzBuzz(Model model){
//
        List<String> fizzBuzz = new ArrayList<>();
        IntStream.range(0, 100).forEach(item -> fizzBuzz.add(""));
        String solution = "";
        int i = 0;
        for (String item: fizzBuzz){
            if (i%15==0){solution = solution + "fizzBuzz" + ", ";}
            else if (i%5==0){solution = solution + "Buzz" + ", ";}
            else if (i%3==0){solution = solution + "fizz" + ", ";}
            else {solution= solution + i + ", ";}
        i=i+1;
        }

        model.addAttribute("solution", solution);
        model.addAttribute("title", "Fizz Buzz");
        return "test/fizzbuzz";
    }
    @RequestMapping(value = "duplicates", method = RequestMethod.GET)
    public String lookForDuplicates(Model model){
//        JSONParser parser = new JSONParser();
        GsonJsonParser parser1 = new GsonJsonParser();

        String fileName = "file/customers.json";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file= new File(classLoader.getResource(fileName).getFile());

        return null;
    }
}
//
//package com.payit.problems;
//
//        import java.util.List;
//
///**
// * Complete the method below. This method will receive a list (as you can see below). The method should iterate over the list. For most
// * indexes you should put the index value into the list at that index. For indexes that are multiples of three, the value should be "Fizz".
// * For indexes that are multiples of five, the value should be "Buzz". For indexes that are multiples of both three and five, the value
// * should be "FizzBuzz".
// */
//public class Probem1 {
//    public List<String> fizzBuzz(List<String> fizzBuzz) {
//        int i = 0;
//        for (String item : fizzBuzz) {
//            if (i % 15 == 0) {
//                fizzBuzz.set(i, "FizzBuzz");
//            } else if (i % 5 == 0) {
//                fizzBuzz.set(i, "Buzz");
//            } else if (i % 3 == 0) {
//                fizzBuzz.set(i, "Fizz");
//            }
//            i+=1;
//        }
//        return fizzBuzz;
//    }
//
//
//    public List<String> otherFizzBuzz(List<String> fizzBuzz) {
//        int i = 0;
//        for (String item : fizzBuzz) {
//            if (i % 15 == 0) {
//                fizzBuzz.set(i, "FizzBuzz");
//            } else if (i % 5 == 0) {
//                fizzBuzz.set(i, "Buzz");
//            } else if (i % 3 == 0) {
//                fizzBuzz.set(i, "Fizz");
//            }
//            else {fizzBuzz.set(i, Integer.toString(i));
//                i+=1;
//            }
//            return fizzBuzz;
//        }
//    }
//
//---------------------------------------------------
//package com.payit.problems;
//
///**
// * For this problem you should complete the method below.
// * This method should determine if a string is a palindrome or not.
// */
//public class Problem2 {
//    public boolean isPalindrome(String value) {
//        int low = 0;
//        int high = value.length();
//
//        while (high > low){
//            if (Character.toLowerCase(value.charAt(low)) != Character.toLowerCase(value.charAt(high))){
//                return false; }
//            low+=1;
//            high-=1;}
//
//        return true}

