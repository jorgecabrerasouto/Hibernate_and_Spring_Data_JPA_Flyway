package co.com.jorgecabrerasouto.sdjpainheritance.domain.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.sdjpainheritance.domain.joined.ElectricGuitar;
import co.com.jorgecabrerasouto.sdjpainheritance.domain.repositories.ElectricGuitarRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	ElectricGuitarRepository electricGuitarRepository;

	@Override
	public void run(String... args) throws Exception {
		ElectricGuitar eg = new ElectricGuitar();
		eg.setNumberOfStrings(6);
		eg.setNumberOfPickups(2);
		electricGuitarRepository.save(eg);

	}
}