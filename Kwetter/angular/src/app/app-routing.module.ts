import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./submodules/users/users.component";
import {TimelineComponent} from "./modules/timeline/timeline.component";
import {DashboardComponent} from "./Views/dashboard/dashboard.component";
import {TweetComponent} from "./Views/tweet/tweet.component";
import {UserComponent} from "./Views/user/user.component";
import {AuthenticationComponent} from "./Views/authentication/authentication.component";



const routes: Routes = [
  {path: 'users', component: UsersComponent},
  { path: '', component: DashboardComponent},
  { path: 'login', component: AuthenticationComponent},
  { path: 'tweet/:id', component: TweetComponent},
  { path: 'user/:username', component: UserComponent},
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
