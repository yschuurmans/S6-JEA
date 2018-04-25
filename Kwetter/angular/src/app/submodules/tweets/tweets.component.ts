import {Component, Input, OnInit} from '@angular/core';
import {Tweet} from "../../domain/tweet";
import {TweetService} from "../../service/tweet.service";

@Component({
  selector: 'app-tweets',
  templateUrl: './tweets.component.html',
  styleUrls: ['./tweets.component.css']
})
export class TweetsComponent implements OnInit {
  @Input() tweets: Tweet[];
  @Input() username: string;

  constructor(private tweetService : TweetService) {
  }

  ngOnInit() {
    if(this.username && this.username.length >=1) {
      this.getTweets();
    }
  }
  getTweets() {
    this.tweetService.getUserTweets(this.username).subscribe(
      tweets => this.tweets = tweets
    );
  }
}


