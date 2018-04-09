import {Component, OnInit, ViewChild} from '@angular/core';
import {TimelineComponent} from "../../modules/timeline/timeline.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @ViewChild(TimelineComponent) timeline : TimelineComponent;

  constructor() { }

  ngOnInit() {
  }

  tweetPosted() {
    this.timeline.refreshTweets();
  }

}
