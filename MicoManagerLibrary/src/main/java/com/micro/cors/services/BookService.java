package com.micro.cors.services;

import java.util.List;

import com.micro.cors.entities.Book;

public interface BookService {
	
	public Book saveBook(Book book);
	
	public Book updateBook(Book book);
	
	public void deleteBook(int bookId);
	
	public List<Book> findBookByTitle(String title);
	
	public List<Book> fingBookByIsbn( String isbn);
	
	public boolean checkIfIdExists(int id);
	
	public List<Book> getBookByCategory(String codeCategpry);

}
