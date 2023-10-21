package hu.example.spring_modulzaro.repo;


import hu.example.spring_modulzaro.model.Person;
import hu.example.spring_modulzaro.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> getOrdersByOrderedBy(Person person);
}
