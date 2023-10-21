package hu.example.spring_modulzaro.controller;

import hu.example.spring_modulzaro.model.Person;
import hu.example.spring_modulzaro.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {
    private final EntityService entityService;

    public PersonController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping({"", "/", "/home"})
    public String getHome(Model model) {
        model.addAttribute("persons", entityService.getAllPerson());
        return "home";
    }

    @GetMapping("/{id}")
    public String getPersonById(Model model, @PathVariable Integer id) {
        Person person = entityService.getPersonById(id);
        model.addAttribute("orders", entityService.getAllOrdersByPerson(person));
        model.addAttribute("person", person);
        return "person";
    }
    @GetMapping("/new")
    public String createPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person_form";
    }

    @PostMapping("/add")
    public String createPerson(@ModelAttribute("person") Person person) {
        entityService.savePerson(person);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Integer id) {
        entityService.deleteOrdersByPerson(entityService.getPersonById(id));
        entityService.deletePersonById(id);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String updatePerson(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("person", entityService.getPersonById(id));
        return "person_update";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Integer id, @ModelAttribute("person") Person updated) {
        entityService.savePerson(updated);
        return "redirect:/person/" + id;
    }

    @GetMapping("/most-order")
    public String getMostOrder(Model model) {
        Person person = entityService.getMostOrdersPerson();
        model.addAttribute("person", person);
        model.addAttribute("orders", entityService.getAllOrdersByPerson(person));
        return "person";
    }

}
