import {Component, OnInit, ViewChild} from '@angular/core';
import {TimelineComponent} from "../../modules/timeline/timeline.component";
import {User} from "../../domain/user";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild(TimelineComponent) timeline : TimelineComponent;
  currentUser: string ;

  constructor() {
  }

  ngOnInit() {
    this.currentUser = localStorage.getItem('username');
  }

  tweetPosted() {
    this.timeline.refreshTweets();
  }

}
