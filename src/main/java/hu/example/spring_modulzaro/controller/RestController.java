package hu.example.spring_modulzaro.controller;

import hu.example.spring_modulzaro.model.Person;
import hu.example.spring_modulzaro.service.EntityService;
import hu.example.spring_modulzaro.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
    private final EntityService entityService;

    public RestController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/people")
    public List<Person> getAllPerson(){
        return entityService.getAllPerson();
    }
    @GetMapping("/orders")
    public List<Order> getAllOrder() {
        return entityService.getAllExpenses();
    }
}
