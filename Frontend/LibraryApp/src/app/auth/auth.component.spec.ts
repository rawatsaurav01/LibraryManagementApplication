import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';

import { AuthComponent } from './auth.component';

describe('AuthComponent', () => {
  let component: AuthComponent;
  let fixture: ComponentFixture<AuthComponent>;
  let authServiceSpy: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('AuthService', ['userSignUp', 'userLogin'], ['isLoginError']);

    TestBed.configureTestingModule({
      declarations: [AuthComponent],
      imports: [ReactiveFormsModule, FormsModule],
      providers: [
        { provide: AuthService, useValue: spy }
      ],
    });

    fixture = TestBed.createComponent(AuthComponent);
    component = fixture.componentInstance;
    authServiceSpy = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call userSignUp method when signUp is called', () => {
    const testData = { 
    name:"Saurav",
    email:"Saurav@test.com",
    password:"saurav@123"
   };
    component.signUp(testData);
    expect(authServiceSpy.userSignUp).toHaveBeenCalledWith(testData);
  });

  it('should call userLogin method and set authError when login is called with an error', () => {
    const testData = { 
    email:"Saurav@test.com",
    password:"saurav@123"

     };
    const isError = true;
    
    // authServiceSpy.isLoginError.and.returnValue({ subscribe: (callback: (isError: boolean) => void) => callback(isError) });

    // component.login(testData);

    expect(authServiceSpy.userLogin).toHaveBeenCalledWith(testData);
    expect(component.authError).toBe("Email or Password is not correct");
  });

  it('should not set authError when login is called without an error', () => {
    const testData = { 
    email:"Saurav@test.com",
    password:"saurav@123"
     };
    const isError = false;
    
    // authServiceSpy.isLoginError.and.returnValue({ subscribe: (callback: (isError: boolean) => void) => callback(isError) });

    // component.login(testData);

    expect(authServiceSpy.userLogin).toHaveBeenCalledWith(testData);
    expect(component.authError).toBe("");
  });

  it('should set showLogin to true when openLogin is called', () => {
    component.showLogin = false;
    component.openLogin();
    expect(component.showLogin).toBe(true);
  });

  it('should set showLogin to false when openSignUp is called', () => {
    component.showLogin = true;
    component.openSignUp();
    expect(component.showLogin).toBe(false);
  });
});