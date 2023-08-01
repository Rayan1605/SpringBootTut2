package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BeerRepository beerRepository;
    Beer testbeer;

    @BeforeEach
    void setUp() {
        testbeer = beerRepository.findAll().get(0);
    }


    @Test
    void testAddCategory(){
        Category savedCat = categoryRepository.save(Category.builder().
                description("Ales")
                .build());

        testbeer.addCategory(savedCat);
        Beer savedBeer = beerRepository.save(testbeer);
        System.out.println(savedBeer.getBeerName());
    }


}










