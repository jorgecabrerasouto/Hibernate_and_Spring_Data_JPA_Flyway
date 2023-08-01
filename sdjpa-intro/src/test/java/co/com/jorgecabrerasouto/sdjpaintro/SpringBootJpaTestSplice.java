package co.com.jorgecabrerasouto.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;

import co.com.jorgecabrerasouto.sdjpaintro.domain.Book;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = { "co.com.jorgecabrerasouto.sdjpaintro.bootstrap" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSplice {

	@Autowired
	BookRepository bookRepository;

	@Commit
	@Order(1)
	@Test
	void testJpaTestSplice() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(2);

		bookRepository.save(new Book("My Book", "1235555", "Self"));

		long countAfter = bookRepository.count();

		assertThat(countBefore).isLessThan(countAfter);
	}

	@Order(2)
	@Test
	void testJpaTestSpliceTransaction() {
		long countBefore = bookRepository.count();
		assertThat(countBefore).isEqualTo(3);

	}
}
