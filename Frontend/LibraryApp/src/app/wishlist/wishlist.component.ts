import { Component, OnInit } from '@angular/core';
import { bookDataType, wishlistArrayDataType, wishlistDataType } from 'src/datatypes';
import { WishlistService } from '../services/wishlist.service';
import {faTrash} from '@fortawesome/free-solid-svg-icons';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  wishlist:undefined|wishlistDataType[];
  
  userName:string="";
  deleteIcon=faTrash;

  constructor(private wishlistService:WishlistService) { }

  ngOnInit(): void {
    
    let userStore=localStorage.getItem('user');
    let emailId=userStore && JSON.parse(userStore);
    // this.userName=emailId;
    console.warn(emailId);

    const tokenStore=localStorage.getItem('jwt');
    let token =tokenStore && JSON.parse(tokenStore);
    const finalToken = 'Bearer ' + token;
    console.warn(finalToken);

    emailId && this.wishlistService.getWishlist(emailId,finalToken).subscribe((data)=>{
      console.warn(data.body);
      this.wishlist=data.body;
    });

  }

  deleteProduct(id:number){
    console.warn(id + " to be deleted");
    const tokenStore=localStorage.getItem('jwt');
    let token =tokenStore && JSON.parse(tokenStore);
    const finalToken = 'Bearer ' + token;
    console.warn(finalToken);

    this.wishlistService.deleteBookFromWishlist(id,finalToken).subscribe((result)=>{
        console.warn(result);
        if(result){
        window.alert("Product is deleted successfully");
        this.list();
      }
    });
  //   this.product.deleteProduct(id).subscribe((result)=>{
  //     console.warn(result);
  //     if(result){
  //       this.productMessage="Product is deleted successfully";
  //       this.list();      
  //     }
      
  //   })

  //   setTimeout(()=>{
  //     this.productMessage=undefined;
  //   },3000);
    
  } 
  list(){
    let userStore=localStorage.getItem('user');
    let emailId=userStore && JSON.parse(userStore);
    // this.userName=emailId;
    console.warn(emailId);

    const tokenStore=localStorage.getItem('jwt');
    let token =tokenStore && JSON.parse(tokenStore);
    const finalToken = 'Bearer ' + token;
    console.warn(finalToken);

    this.wishlistService.getWishlist(emailId,finalToken).subscribe((data)=>{
      console.warn(data.body);
      this.wishlist=data.body;
    });


  }


}
