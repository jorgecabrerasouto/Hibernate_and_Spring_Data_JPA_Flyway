package co.com.jorgecabrerasouto.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.sdjpaintro.domain.AuthorUuid;
import co.com.jorgecabrerasouto.sdjpaintro.domain.Book;
import co.com.jorgecabrerasouto.sdjpaintro.domain.BookUuid;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.AuthorUuidRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookNaturalRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookUuidRepository;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
	
	private final BookRepository bookRepository;
	private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;
    private final BookNaturalRepository bookNaturalRepository;
	
	public DataInitializer(BookRepository bookRepository, 
			AuthorUuidRepository authorUuidRepository, 
			BookUuidRepository bookUuidRepository, 
			BookNaturalRepository bookNaturalRepository) {
		this.bookRepository = bookRepository;
		this.authorUuidRepository = authorUuidRepository;
		this.bookUuidRepository = bookUuidRepository;
		this.bookNaturalRepository = bookNaturalRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.deleteAll();
		
		Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse", null);
		Book savedDDD = bookRepository.save(bookDDD);
		
		Book bookSIA = new Book("Spring in Action", "234234", "Oriely", null);	
		Book savedSIA = bookRepository.save(bookSIA);
		
		bookRepository.findAll().forEach(book -> {
			System.out.println("Book Id: " + book.getId());
			System.out.println("Book Title: " + book.getTitle());	
		});
		
        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Buck");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthor.getId() );
        
        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All About UUIDs");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId());
	}

}
