import { EventEmitter, Injectable } from '@angular/core';
import { loginDataType, signUpDataType } from 'src/datatypes';
import {HttpClient} from '@angular/common/http';
import { JsonPipe } from '@angular/common';
import { Router } from '@angular/router';
import { outputAst } from '@angular/compiler';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isUserLoggedIn=new BehaviorSubject<boolean>(false);

  isLoginError=new EventEmitter<boolean>(false);
  isSignUpError=new EventEmitter<boolean>(true);

  constructor(private http:HttpClient,private router:Router) { }

  findUserByEmail(emailId:string){
      return this.http.get<signUpDataType>(`http://34.235.207.182:8000/user/${emailId}`).subscribe((result)=>{
      console.warn(result.name);
      localStorage.setItem('userName',result.name);
    })
  }

  userSignUp(data:signUpDataType){
    console.warn(data);
    this.http.post('http://34.235.207.182:8000/user/register',data,{observe:'response'}).subscribe((result)=>{
      console.warn(result);
      if(result){
        window.alert("User Registered Successfully");
        this.router.navigate(['/']);
        this.isSignUpError.emit(false);
        
        
      }
    },
    (err)=>{
      window.alert("User Not Registered!!!!!");
    }
    )
  }

  userLogin(data:loginDataType){
    this.http.post('http://34.235.207.182:8000/login',data,
    {observe:'response'}).subscribe((result:any)=>{

      if(result&&result.body){
      //   this.isLoginError.emit(false);
        localStorage.setItem('user',JSON.stringify(data.email));

        // localStorage.setItem('accessToken', res.accessToken);

        // console.warn(JSON.stringify(result.body.jwt_token));
        localStorage.setItem('jwt',(JSON.stringify(result.body.jwt_token)));
        window.alert("user logged in successfully");
        this.router.navigate(['/home']);
      }
      
    },
    (err:any)=>{
    
      console.warn("Login Failed");
      window.alert("user logged in failed");
      this.isLoginError.emit(true);
  })
  }

  getuserToken(){
    

  }
  setUsername(emailId:string){
    this.findUserByEmail(emailId);

  }
  getUsername(){
    return localStorage.getItem('userName');

  }

}
