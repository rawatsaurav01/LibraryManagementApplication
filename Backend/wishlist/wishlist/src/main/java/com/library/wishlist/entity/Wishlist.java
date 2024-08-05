package com.library.wishlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="wishlistTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

	@Schema(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishlistId;
	private String title;
	private String summary;
	private String language;
	private String copyright;
	private String seriesName;
	private int page_count;
	@Schema(hidden = true)
	private String emailId;

	private String canonical_isbn;
}
