package com.library.booksprofile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.booksprofile.entity.Books;
import com.library.booksprofile.service.BooksService;

@RestController
@RequestMapping("/books")
//@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BooksService booksService;
	
	@GetMapping("")
	public Books getAllBooks() {
		return this.booksService.getAllBooks();
	}
	
	@GetMapping("/title/{bookTitle}")
	public Books  getBooksByTitle(@PathVariable("bookTitle") String bookTitle){
		return this.booksService.getBookByTitle(bookTitle);
	}
	
//	public Results addBookToWishlist(){
//		return 
//	}
	
	@GetMapping("/books/type/{bookType}")
	public Books  getBooksByType(@PathVariable("bookType") String bookType){
		return this.booksService.getBookByTitle(bookType);
	}
	
	
}
