import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TweetsComponent } from './tweets/tweets.component';
import { UsersComponent } from './users/users.component';
import {UserService} from "./service/user.service";
import {TweetService} from "./service/tweet.service";
import {HttpClientModule} from "@angular/common/http";
import { TimelineComponent } from './timeline/timeline.component';
import { AppRoutingModule } from './/app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    TweetsComponent,
    UsersComponent,
    TimelineComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [UserService, TweetService],
  bootstrap: [AppComponent]
})
export class AppModule { }
