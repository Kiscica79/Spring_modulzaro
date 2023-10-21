package hu.example.spring_modulzaro.service;

import hu.example.spring_modulzaro.model.Person;
import hu.example.spring_modulzaro.repo.OrderRepo;
import hu.example.spring_modulzaro.repo.PersonRepo;
import hu.example.spring_modulzaro.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntityService {

    private OrderRepo orderRepo;
    private PersonRepo personRepo;

    public EntityService(OrderRepo orderRepo, PersonRepo personRepo) {
        this.orderRepo = orderRepo;
        this.personRepo = personRepo;
    }

    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    public List<Order> getAllExpenses() {
        return orderRepo.findAll();
    }


    public Person getPersonById(Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    public List<Order> getAllOrdersByPerson(Person person) {
        return orderRepo.getOrdersByOrderedBy(person);
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    @Transactional
    public void deleteOrdersByPerson(Person person) {
        List<Order> orders = orderRepo.getOrdersByOrderedBy(person);
        orderRepo.deleteAll(orders);
    }

    @Transactional
    public void deletePersonById(Integer id) {
        personRepo.deleteById(id);
    }

    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    public Person getMostOrdersPerson() {
        Map<Person, Integer> personOrders = fillPersonsMap();
        return getMaxOrdersFromPersonOrdersMap(personOrders);
    }

    private Person getMaxOrdersFromPersonOrdersMap(Map<Person, Integer> personOrders) {
        int max = 0;
        for (var actual : personOrders.entrySet()) {
            if (actual.getValue() > max) {
                max = actual.getValue();
            }
        }
        for (var actual : personOrders.entrySet()) {
            if (actual.getValue() == max) {
                return actual.getKey();
            }
        }
        return null;
    }

    private Map<Person, Integer> fillPersonsMap() {
        Map<Person, Integer> personOrders = new HashMap<>();
        fillPersonsMapWithPerson(personOrders);
        fillPersonsMapWithOrders(personOrders);
        return personOrders;
    }

    private void fillPersonsMapWithOrders(Map<Person, Integer> personOrders) {
        List<Order> orders = getAllExpenses();
        for (Order actual : orders) {
            Person actualPerson = actual.getOrderedBy();
            personOrders.put(actualPerson, (int) (personOrders.get(actualPerson) + actual.getPrice()));
        }
    }

    private void fillPersonsMapWithPerson(Map<Person, Integer> personOrders) {
        List<Person> persons = getAllPerson();
        for (Person actual : persons) {
            personOrders.put(actual, 0);
        }
    }

}
