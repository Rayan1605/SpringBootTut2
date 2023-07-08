package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.BeerOrder;
import guru.springframework.spring6restmvc.entities.BeerOrderShipment;
import guru.springframework.spring6restmvc.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
    @Test
    void testBeerOrder(){
BeerOrder beerOrder = BeerOrder.builder().customerRef("Test Order").customer(testCustomer)
        .beerOrderShipment(BeerOrderShipment.builder().trackingNumber("12345r").build()).build();

BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);
//his method is responsible for saving changes made to an object and immediately flushing them to the underlying database.
//
//Here's what the saveAndFlush() operation generally entails:
//
//Saving: The saveAndFlush() operation takes an object (in this case, beerOrder) and persists
// it to the database. This usually involves inserting a new record if the object is not yet
// present in the database or updating an existing record if changes have been made to the object.
//
//Flushing: After the save operation, the changes are flushed, meaning they are immediately
// written to the database. Flushing ensures that the changes are persisted and visible to other
// parts of the application or other database connections.

        System.out.println(savedBeerOrder.getCustomerRef());

    }
}











