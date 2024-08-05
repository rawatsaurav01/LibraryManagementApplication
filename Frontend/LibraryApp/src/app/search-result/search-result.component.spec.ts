import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { bookDataType } from 'src/datatypes';
import { BookServiceService } from '../services/book-service.service';

import { SearchResultComponent } from './search-result.component';

describe('SearchResultComponent', () => {
  let component: SearchResultComponent;
  let fixture: ComponentFixture<SearchResultComponent>;
  let activatedRouteMock: Partial<ActivatedRoute>;
  let bookServiceSpy: jasmine.SpyObj<BookServiceService>;

  beforeEach(() => {
    const bookServiceMock = jasmine.createSpyObj('BookServiceService', ['searchBookByTitle']);

    TestBed.configureTestingModule({
      declarations: [SearchResultComponent],
      providers: [
        { provide: ActivatedRoute, useValue: { snapshot: { paramMap: { get: () => 'mockQuery' } } } },
        { provide: BookServiceService, useValue: bookServiceMock },
      ],
    });

    fixture = TestBed.createComponent(SearchResultComponent);
    component = fixture.componentInstance;
    activatedRouteMock = TestBed.inject(ActivatedRoute);
    bookServiceSpy = TestBed.inject(BookServiceService) as jasmine.SpyObj<BookServiceService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch search results on initialization', () => {
    const mockResults: bookDataType[] = [{
      canonical_isbn:"23232323232",
      copyright:"2023",
      language:"english",
      page_count:1221,
      seriesName:"Harry Potter",
      summary:"a very long data",
      title:"Harry Potter"
    }];
    bookServiceSpy.searchBookByTitle.and.returnValue(of({ results: mockResults }));

    component.ngOnInit();

    expect(component.searchResult).toEqual(mockResults);
  });

  it('should not call searchBookByTitle if query is not available', () => {
    // spyOn(activatedRouteMock.snapshot.paramMap, 'get').and.returnValue(undefined);

    component.ngOnInit();

    expect(bookServiceSpy.searchBookByTitle).not.toHaveBeenCalled();
  });
});
