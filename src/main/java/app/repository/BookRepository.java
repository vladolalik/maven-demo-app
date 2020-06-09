package app.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.model.Book;

@Repository
@Profile({"h2", "postgres"}) // the db profiles
public interface BookRepository extends JpaRepository<Book, Long> {
	
	//For more custom JPA methods, refer to JPA doc for the method naming conversions
	//Ref: https://docs.spring.io/spring-data/jpa/docs/2.2.4.RELEASE/reference/html/#jpa.query-methods.query-creation
	//For example, this will translate to following query:

}
