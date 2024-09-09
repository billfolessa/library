package com.micro.cors.reposiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.micro.cors.entities.Book;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookRepo extends JpaRepository<Book,Integer> {
	
	public List<Book> findByIsbnIgnorecase(String isbn);
	
	public List<Book> findByTitle(String title);
	
	@Query("select b"
			+ "from Book b"
			+ "INNER JOIN b.category cat"
			+ "where cat.code =: code")
	public List<Book> findByCategory(@Param("code") String codeCategory);

}
