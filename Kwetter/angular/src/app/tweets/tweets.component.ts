import { Component, OnInit } from '@angular/core';
import {Tweet} from "../domain/tweet";
import {TweetService} from "../service/tweet.service";

@Component({
  selector: 'app-tweets',
  templateUrl: './tweets.component.html',
  styleUrls: ['./tweets.component.css']
})
export class TweetsComponent implements OnInit {
  tweets : Tweet[] = [];
  constructor(private tweetService: TweetService) { }

  ngOnInit() {
    this.getAllTweets();
  }

  getAllTweets() : void {
    this.tweetService.getAllTweets()
      .subscribe(tweets => this.tweets = tweets.slice(1, 5));
  }

}
