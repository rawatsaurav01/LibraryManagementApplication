import { Component, OnInit } from '@angular/core';
import { bookDataType } from 'src/datatypes';
import { BookServiceService } from '../services/book-service.service';
import { WishlistService } from '../services/wishlist.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  allBooks:undefined | bookDataType[];
  successMessage:string="Items added to wishlist";
  failureMessage:string="Already added to Wishlist";

  results:bookDataType[]|any;

  constructor(private book:BookServiceService,private wishlist:WishlistService) { }

  ngOnInit(): void {

    this.book.booksList().subscribe((data)=>{
      console.warn(data.results);
      this.allBooks=data.results;
    });
  }


  viewDetails(data:bookDataType){
  console.warn(data);
  this.book.viewBookDetails();

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
