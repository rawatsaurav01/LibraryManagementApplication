import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { bookDataType, wishlistArrayDataType, wishlistDataType } from 'src/datatypes';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  constructor(private http:HttpClient) { }

  getWishlist(emailId:string,token:string){
    // console.warn(token);
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.get<wishlistArrayDataType>(`http://34.235.207.182:8000/wishlist/getbyemailid/${emailId}`,options);
  }

  addBookToWishlist(bookData:bookDataType,token:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.post<bookDataType>(`http://34.235.207.182:8000/wishlist/additem`,bookData,options);
  }

  deleteBookFromWishlist(wishlistId:number,token:string){
    let options = {
      headers:{"Authorization":token}
    }
    return this.http.delete(`http://34.235.207.182:8000/wishlist/delete/${wishlistId}`,options);
  }

}
