import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { bookDataType, booksArrayDataType } from 'src/datatypes';


@Injectable({
  providedIn: 'root'
})

export class BookServiceService {
  

  constructor(private http:HttpClient,private router:Router) { }

  booksList(){
    return this.http.get<booksArrayDataType>('http://34.235.207.182:8000/books');
  }

  searchBookByTitle(token:string,query:string){
    let options = {
      headers:{"Authorization":token}
    }

    console.warn(query);
    return this.http.get<booksArrayDataType>(`http://34.235.207.182:8000/books/title/${query}`,options);
  }

  viewBookDetails(){
    this.router.navigate(['/book-detail']);
  }

}
