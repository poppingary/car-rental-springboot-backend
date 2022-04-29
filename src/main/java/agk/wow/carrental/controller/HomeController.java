package agk.wow.carrental.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class HomeController {
    @GetMapping(value = "/homepage")
    public String greetingWord() {
        return "Welcome!";
    }
}