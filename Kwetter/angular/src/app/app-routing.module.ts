import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {TimelineComponent} from "./timeline/timeline.component";



const routes: Routes = [
  {path: 'users', component: UsersComponent},
  { path: '', component: TimelineComponent},
];

@NgModule({
  imports: [
    CommonModule,
    [ RouterModule.forRoot(routes) ]
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule { }
