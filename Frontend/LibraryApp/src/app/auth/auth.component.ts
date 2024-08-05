import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { loginDataType, signUpDataType } from 'src/datatypes';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  loginForm!: FormGroup;
  signUpForm!: FormGroup;
  
  authError:string="";
  // signUpForm=new FormGroup({
  //   username:new FormControl('',[Validators.required]),
  //   password:new FormControl(''),
  //   email:new FormControl('')

  // })

  showLogin=true;

  constructor(private authService:AuthService,private fb: FormBuilder ) {
  }
  ngOnInit(): void {
    this.createLoginForm();
    this.createSignUpForm();
  }


  createLoginForm() {
    this.loginForm = this.fb.group({
      // Write Code here for following validations:
      // - All fields are required.
      // - 'firstName' and 'lastName' fields contain only text (no numbers or special characters).
      // firstName :['',[Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      // lastName :['',[Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      // - Email validation for the email field.
      email :['',[Validators.required, Validators.email]],
      // - Password should have a minimum length of 8 characters.
      // - Password should contains at least one uppercase letter and one special character.
      password: [
        '',[Validators.required,Validators.minLength(8),Validators.pattern(/^(?=.*[A-z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]*$/)]
      ]
    });
  }

  createSignUpForm() {
    this.signUpForm = this.fb.group({
      // Write Code here for following validations:
      // - All fields are required.
      // - 'firstName' and 'lastName' fields contain only text (no numbers or special characters).
      name :['',[Validators.required, Validators.pattern('[a-zA-Z ]*')]],


        // - Password should have a minimum length of 8 characters.
      // - Password should contains at least one uppercase letter and one special character.
      password: [
        '',[Validators.required,Validators.minLength(8),Validators.pattern(/^(?=.*[A-z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]*$/)]
      ],
      // - Email validation for the email field.
      email :['',[Validators.required, Validators.email]]
    
    
    });
  }

  onSubmit():void {
    if (this.loginForm.valid) {
      // Form is valid, handle the submission (e.g., send data to a server)
      console.warn(this.loginForm.value);
      this.authService.userLogin(this.loginForm.value);
      
      this.authService.isLoginError.subscribe((isError:any)=>{
  
        console.warn(isError);
        if(isError){
              this.authError="Email or Password is not correct"
            }
        else{
          console.log("email and password are correct");
        }
  
      });
      
      
    } else {
      // Form is invalid, mark fields as touched to display error messages
      this.markFormGroupTouched(this.loginForm);
    }
  }
  markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }




 

  // get userFieldData(){
  //   return this.signUpForm.get('user');
  // }

  onSignUp():void{

    if (this.signUpForm.valid) {
      // console.warn(data);
      this.authService.userSignUp(this.signUpForm.value);

      this.authService.isSignUpError.subscribe((result:any)=>{
        if(result){
          this.signUpForm.reset();
        }

      })
    }

  }

  signUp(data:signUpDataType):void{
    console.warn(data);
    this.authService.userSignUp(data);
   }

  // login(data:loginDataType):void{
    // console.warn(data);
    // this.authService.userLogin(data);
    // this.authService.isLoginError.subscribe((isError:any)=>{

    //   console.warn(isError);
    //   if(isError){
    //         this.authError="Email or Password is not correct"
    //       }

    // });
    // this.seller.isLoginError.subscribe((isError)=>{
    //   // console.warn(isError);
    //   if(isError){
    //     this.authError="Email or Password is not correct"
    //   }
    // });
  // }

  openLogin(){
    this.showLogin=true;
  }

  openSignUp(){
    this.showLogin=false;
  }

}
