package guru.springframework.spring6restmvc.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest // this will bring up an embedded database and configure Spring Data JPA for us
class BeerOrderRepositoryTest {
 @Autowired // this will inject the BeerOrderRepository for us
    BeerOrderRepository  beerOrderRepository;
@Autowired// this will inject the CustomerRepository for us
 CustomerRepository customerRepository;

    @Test
    void testBeerOrder(){


    }
}