package hu.example.spring_modulzaro.controller;

import hu.example.spring_modulzaro.model.Order;
import hu.example.spring_modulzaro.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private EntityService entityService;

    public OrderController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/new/{person-id}")
    public String createExpense(Model model, @PathVariable("person-id") Integer personId) {
        model.addAttribute("personId", personId);
        model.addAttribute("order", new Order());
        return "order_form";
    }

    @PostMapping("/add/{person-id}")
    public String createOrder(@ModelAttribute("order")Order order, @PathVariable("person-id") Integer personId) {
        order.setOrderedBy(entityService.getPersonById(personId));
        entityService.saveOrder(order);
        return "redirect:/home";
    }
}
