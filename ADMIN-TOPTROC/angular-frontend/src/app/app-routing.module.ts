import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ObjectDetailsComponent } from './object-details/object-details.component';
import { ObjectListComponent } from './object-list/object-list.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';


const routes: Routes = [
  {path: 'users', component: UserListComponent},
  {path: 'user-details/:id', component: UserDetailsComponent},
  {path: 'objects', component: ObjectListComponent},
  {path: 'object-details/:id', component: ObjectDetailsComponent},
  {path: '', redirectTo: 'users', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
