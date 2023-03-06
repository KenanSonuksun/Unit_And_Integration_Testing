package testing.Testing.controllers;

import org.springframework.web.bind.annotation.*;
import testing.Testing.models.Person;

import java.util.List;

@RestController
public class TextController {

    @GetMapping("/getText")
    public String getText(@RequestParam(name = "name",defaultValue = "Jack")String name){
        return String.format("Tested by %s",name);
    }

    @PostMapping("/postPerson")
    public List<Person> postPerson(@RequestBody List<Person> personList) {
        return personList;
    }
}
