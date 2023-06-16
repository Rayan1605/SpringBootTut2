package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;


    @Test
    void testGetBeerListByName(){
        //This is a sql convention and the %  are wildcard and the wildcard allows  us to search for
        // anything that contains IPA and it will put in that wildcard
        //also that findAllByBeerNameIsLikeIgnoreCase is a jpa method that is created by spring data jpa
        //we configured that in our BeerRepository interface
        List<Beer> list = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");
    }
    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy BeerMy Beerv").
                beerStyle(BeerStyle.GOSE).upc("123123123123L")
                        .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}