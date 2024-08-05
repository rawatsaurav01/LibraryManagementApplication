package com.library.booksprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.booksprofile.entity.Books;

@Service
public class BooksService {

	// add your api key here
	private static final String API_KEY = "73c4064e20msh9f9a58f29ab0a5dp144ebcjsnb8ec12a0e67e";

	// add the base api url here
	private static final String API_URL = "https://book-finder1.p.rapidapi.com/api/search";

	private final RestTemplate restTemplate;

	public BooksService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Books getAllBooks() {
		String url = API_URL + "?rapidapi-key=" + API_KEY;
		return restTemplate.getForObject(url, Books.class);
	}

	public Books getBookByTitle(String bookTitle) {
		String url = API_URL + "?rapidapi-key=" + API_KEY + "&title=" + bookTitle;
		return restTemplate.getForObject(url, Books.class);
	}

	public Books getBookByType(String bookType) {
		String url = API_URL + "?rapidapi-key=" + API_KEY + "&type=" + bookType;
		return restTemplate.getForObject(url, Books.class);
	}

}
