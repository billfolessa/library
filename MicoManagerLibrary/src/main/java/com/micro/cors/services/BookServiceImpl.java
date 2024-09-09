package com.micro.cors.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.cors.entities.Book;
import com.micro.cors.reposiry.BookRepo;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepo bookRepo;

	@Override
	public Book saveBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public void deleteBook(int bookId) {
		bookRepo.deleteById(bookId);
	}
	
	@Override
	public List<Book> findBookByTitle(String title) {
		return bookRepo.findByTitle(title);
	}

	@Override
	public List<Book> fingBookByIsbn(String isbn) {
		return bookRepo.findByIsbnIgnorecase(isbn);
	}

	@Override
	public boolean checkIfIdExists(int id) {
		// TODO Auto-generated method stub
		return bookRepo.existsById(id);
	}

	@Override
	public List<Book> getBookByCategory(String codeCategpry) {
		return getBookByCategory(codeCategpry);
	}

}
