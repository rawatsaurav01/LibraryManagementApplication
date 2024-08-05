import { Component, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { bookDataType } from 'src/datatypes';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userName: string="";
  dataInLocalStorage:any;

  menuType:string='default';

  constructor(private route:Router,private authService:AuthService) { }

  ngOnInit(): void {
    this.dataInLocalStorage=localStorage.getItem('user');

    if(localStorage.getItem('user')){
      this.menuType='loggedIn';
    }
    else{
      this.menuType='default';
    }

    //get data from local storage
    if(localStorage.getItem('user')){
      let userStore=localStorage.getItem('user');
      let emailId=userStore && JSON.parse(userStore);


    const tokenStore=localStorage.getItem('jwt');
    let token =tokenStore && JSON.parse(tokenStore);
    const finalToken = 'Bearer ' + token;

    // console.warn(finalToken);

      this.authService.findUserByEmail(emailId);
      
      //set username to localStorage
      this.authService.setUsername(emailId);

      //getUsername from localStorage
       let userName= this.authService.getUsername();

       //finally set the username to header
        if(userName!=undefined){
          this.userName=userName;
        }
        
      // this.userName=userName;
      
    }
  }


  submitSearch(query:string){
    // let userStore=localStorage.getItem('user');
    //   let emailId=userStore && JSON.parse(userStore);


    // const tokenStore=localStorage.getItem('jwt');
    // let token =tokenStore && JSON.parse(tokenStore);
    // const finalToken = 'Bearer ' + token;

    // this.bookService.searchBookByTitle(finalToken,query);
    this.ngOnInit();
    this.route.navigate([`/search/${query}`]);

  }

  logout(){
    this.menuType="default";
    localStorage.clear();
    this.ngOnInit();
    this.route.navigate(['/']);
  }

}
