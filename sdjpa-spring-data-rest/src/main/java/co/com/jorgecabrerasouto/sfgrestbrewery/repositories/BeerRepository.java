package co.com.jorgecabrerasouto.sfgrestbrewery.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sfgrestbrewery.domain.Beer;
import co.com.jorgecabrerasouto.sfgrestbrewery.domain.BeerStyleEnum;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
	
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}
