import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Tweet} from "../../domain/tweet";
import {TweetService} from "../../service/tweet.service";

@Component({
  selector: 'app-post-tweet',
  templateUrl: './post-tweet.component.html',
  styleUrls: ['./post-tweet.component.css']
})
export class PostTweetComponent implements OnInit {

  @Output() tweetPosted = new EventEmitter<void>();
  @Input() username: String;
  tweet : Tweet;

  constructor(private tweetService : TweetService) { }

  ngOnInit() {
    this.tweet = new Tweet();
  }

  postTweet() {
    console.log("Post tweet");
    console.log("Tweet Content:" + this.tweet.tweetContent);

    this.tweetService.postTweet(this.username, this.tweet).subscribe(
       x=> this.tweetPosted.next()
    );

  }
}
