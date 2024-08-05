import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { WishlistComponent } from './wishlist/wishlist.component';

const routes: Routes = [
  {
    component:HomeComponent,
    path:'home',
    canActivate:[AuthGuard]
  },
  {
    component:AuthComponent,
    path:''
  },
  {
    component:WishlistComponent,
    path:'wishlist',
    canActivate:[AuthGuard]
  },
  {
    component:SearchResultComponent,
    path:'search/:query',
    canActivate:[AuthGuard]
  },
  {
    component:BookDetailComponent,
    path:'book-detail'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
