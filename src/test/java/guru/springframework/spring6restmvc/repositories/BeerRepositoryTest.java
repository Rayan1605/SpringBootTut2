package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.bootstrap.BootstrapData;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerCSVRecord;
import guru.springframework.spring6restmvc.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BootstrapData.class, BeerCSVRecord.class}) // this is to import the bootstrap data class
    // so that we can use it in our test and also the BeerCSVRecord class so that we can use it in our test
    // it will contain the data that we will use in our test
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;


    @Test
    void testGetBeerListByName(){
        //This is a sql convention and the %  are wildcard and the wildcard allows  us to search for
        // anything that contains IPA and it will put in that wildcard
        //also that findAllByBeerNameIsLikeIgnoreCase is a jpa method that is created by spring data jpa
        //we configured that in our BeerRepository interface
        List<Beer> list = (List<Beer>) beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%", null);

         assertThat(list.size()).isEqualTo(30);
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