import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TweetsComponent } from './submodules/tweets/tweets.component';
import { UsersComponent } from './submodules/users/users.component';
import {UserService} from "./service/user.service";
import {TweetService} from "./service/tweet.service";
import {HttpClientModule} from "@angular/common/http";
import { TimelineComponent } from './modules/timeline/timeline.component';
import { AppRoutingModule } from './/app-routing.module';
import { DashboardComponent } from './Views/dashboard/dashboard.component';
import { SearchComponent } from './modules/search/search.component';
import { FormsModule } from '@angular/forms';
import { FollowersComponent } from './modules/followers/followers.component';
import { PostTweetComponent } from './modules/post-tweet/post-tweet.component';
import { TweetComponent } from './Views/tweet/tweet.component';
import { UserComponent } from './Views/user/user.component';
import { LoginComponent } from './submodules/login/login.component'
import { CookieService } from 'ngx-cookie-service';


@NgModule({
  declarations: [
    AppComponent,
    TweetsComponent,
    UsersComponent,
    TimelineComponent,
    DashboardComponent,
    SearchComponent,
    FollowersComponent,
    PostTweetComponent,
    TweetComponent,
    UserComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [UserService, TweetService, CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
