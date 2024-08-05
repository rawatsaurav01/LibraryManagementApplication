import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { bookDataType } from 'src/datatypes';
import { BookServiceService } from '../services/book-service.service';
import { WishlistService } from '../services/wishlist.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  searchResult:undefined| bookDataType[];

  constructor(private activatedRoute:ActivatedRoute,private bookService:BookServiceService,private wishlist:WishlistService) { }

  ngOnInit(): void {
    let query=this.activatedRoute.snapshot.paramMap.get('query');

    const tokenStore=localStorage.getItem('jwt');
    let token =tokenStore && JSON.parse(tokenStore);
    const finalToken = 'Bearer ' + token;
    console.warn(finalToken);

    query&&this.bookService.searchBookByTitle(finalToken,query).subscribe((result)=>{
      this.searchResult=result.results;
  });



  }

  

  viewDetails(data:bookDataType){
    console.warn(data);
    this.bookService.viewBookDetails();
  
    }
  
    addBookToWishlist(data:bookDataType){
  
      const tokenStore=localStorage.getItem('jwt');
      let token =tokenStore && JSON.parse(tokenStore);
      const finalToken = 'Bearer ' + token;
      console.warn(data);
      this.wishlist.addBookToWishlist(data,finalToken).subscribe(result=>{
        console.warn(result);
        if(result){
          window.alert('Book added to Wishlist');
        }
      },
      (err)=>{
        window.alert('Book already in Wishlist');
      });
    }
  

}
