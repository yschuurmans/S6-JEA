import {Component, OnInit, ViewChild} from '@angular/core';
import {TimelineComponent} from "../../modules/timeline/timeline.component";
import {User} from "../../domain/user";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild(TimelineComponent) timeline : TimelineComponent;
  currentUser: string;

  constructor(cookieService:CookieService) {
    this.currentUser = cookieService.get('username');
  }

  ngOnInit() {
  }

  tweetPosted() {
    this.timeline.refreshTweets();
  }

}
