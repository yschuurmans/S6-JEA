import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./submodules/users/users.component";
import {TimelineComponent} from "./modules/timeline/timeline.component";
import {DashboardComponent} from "./dashboard/dashboard.component";



const routes: Routes = [
  {path: 'users', component: UsersComponent},
  { path: '', component: DashboardComponent},
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
