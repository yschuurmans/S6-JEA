import {Component, OnInit, ViewChild} from '@angular/core';
import {TimelineComponent} from "../../modules/timeline/timeline.component";
import {User} from "../../domain/user";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild(TimelineComponent) timeline : TimelineComponent;
  currentUser: string ;

  constructor() {
    this.currentUser = localStorage.getItem('username');
  }

  ngOnInit() {
  }

  tweetPosted() {
    this.timeline.refreshTweets();
  }

}
