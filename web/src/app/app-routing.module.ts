import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { SearchComponent } from './search/search.component';
import { UserCommentsComponent } from './user-comments/user-comments.component';
import { DetailsComponent } from './details/details.component';
import { Error404Component } from './error404/error404.component';
import { AuthGuardService } from './services/auth-guard.service';
import { UserManagementComponent } from './user-management/user-management.component';


const routes: Routes = [
  {path: "", component: IndexComponent},
  {path: "search", component: SearchComponent, canActivate : [AuthGuardService] },
  {path: "profile/:user", component: UserCommentsComponent, canActivate : [AuthGuardService]},
  {path: "game/:title", component: DetailsComponent, canActivate : [AuthGuardService]},
  {path: "users", component: UserManagementComponent, canActivate : [AuthGuardService]},
  {path: "**", component: Error404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
