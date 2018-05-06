import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Tweet} from "../../domain/tweet";
import {TweetService} from "../../service/tweet.service";

@Component({
  selector: 'app-post-tweet',
  templateUrl: './post-tweet.component.html',
  styleUrls: ['./post-tweet.component.css']
})
export class PostTweetComponent implements OnInit {

  @Input() username: String;
  tweet : Tweet;

  constructor(private tweetService : TweetService) { }

  ngOnInit() {
    this.tweet = new Tweet();
  }

  postTweet() {

    this.tweetService.postTweet(this.username, this.tweet).subscribe(
       x=> this.tweetSuccesfullyPosted()
    );

  }

  tweetSuccesfullyPosted(){
    this.tweet.tweetContent = "";
  }
}
