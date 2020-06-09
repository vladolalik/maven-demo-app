package app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import app.dto.BookDTO;
import app.exception.BookNotFoundException;
import app.model.Book;
import app.repository.BookRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
    BookRepository bookRepository;

    @GetMapping()
    public List<Book> getAllBooks() {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        return bookRepository.findAll(sort);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {            
            throw new BookNotFoundException(String.format("Book with id %s not found.", bookId));
        }
        return optionalBook.get();
    }

    @PutMapping()
    public Book updateBook(@RequestBody Book book) {
        Optional<Book> optionalBook =  bookRepository.findById(book.getId());
        if (!optionalBook.isPresent()) {            
            throw new BookNotFoundException(String.format("Book with id %s not found.", book.getId()));
        }
        bookRepository.save(book);
        return book;
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setPublished(bookDTO.getPublished());
        book.setTitle(bookDTO.getTitle());
        Book savedBook = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedBook.getId())
            .toUri();
        return ResponseEntity.created(location).build();
    }
}