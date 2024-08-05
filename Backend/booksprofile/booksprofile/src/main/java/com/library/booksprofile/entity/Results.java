package com.library.booksprofile.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Results{

	private String title;
	private String summary;
	private String language;
	private String copyright;
	private String seriesName;
	private int page_count;
	private String canonical_isbn;
	
}
