import {Component, Input, OnInit} from '@angular/core';
import {TweetService} from "../../service/tweet.service";
import {Tweet} from "../../domain/tweet";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  tweets : Tweet[] = [];
  @Input() username: String;

  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.refreshTweets();
  }

  getTimelineTweets(userID : String) : void {
      this.tweetService.getTimelineTweets(userID).subscribe(
        tweets => this.tweets = tweets
      );
  }

  refreshTweets() {
    console.log("refreshing tweets");
    this.getTimelineTweets(this.username);
  }
}
