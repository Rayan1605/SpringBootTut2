package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerOrderRepositoryTest {
 @Autowired // this will inject the BeerOrderRepository for us
    BeerOrderRepository  beerOrderRepository;
@Autowired// this will inject the CustomerRepository for us
 CustomerRepository customerRepository;

@Autowired
    BeerRepository beerRepository;

Customer testCustomer;
Beer testBeer;

@BeforeEach
void setup(){
testCustomer = customerRepository.findAll().get(0);
testBeer = beerRepository.findAll().get(0);
}

    @Test
    void testBeerOrder(){


    }
}










