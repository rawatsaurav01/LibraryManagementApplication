import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { AuthService } from '../services/auth.service';

import { HomeComponent } from './home.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let routerSpy: jasmine.SpyObj<Router>;
  let authServiceSpy: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    const routerMock = jasmine.createSpyObj('Router', ['navigate']);
    const authServiceMock = jasmine.createSpyObj('AuthService', ['findUserByEmail']);

    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      providers: [
        { provide: Router, useValue: routerMock },
        { provide: AuthService, useValue: authServiceMock }
      ],
    });

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
    authServiceSpy = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to search page on submitSearch', () => {
    const query = 'testQuery';
    component.submitSearch(query);

    expect(routerSpy.navigate).toHaveBeenCalledWith([`/search/${query}`]);
  });

  it('should log out and navigate to auth page on logout', () => {
    component.logout();

    expect(localStorage.removeItem).toHaveBeenCalledWith('user');
    expect(localStorage.removeItem).toHaveBeenCalledWith('jwt');
    expect(localStorage.removeItem).toHaveBeenCalledWith('userName');
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/auth']);
  });

  it('should initialize user details when user is in local storage', () => {
    spyOn(localStorage, 'getItem').and.returnValue('user');
    spyOn(localStorage, 'getItem').and.returnValue('jwt');
    spyOn(localStorage, 'getItem').and.returnValue('userName');

    component.ngOnInit();

    expect(authServiceSpy.findUserByEmail).toHaveBeenCalled();
    expect(component.userName).toEqual('userName');
  });

  it('should not initialize user details when user is not in local storage', () => {
    spyOn(localStorage, 'getItem').and.returnValue(null);

    component.ngOnInit();

    expect(authServiceSpy.findUserByEmail).not.toHaveBeenCalled();
    expect(component.userName).toEqual('');
  });
});
