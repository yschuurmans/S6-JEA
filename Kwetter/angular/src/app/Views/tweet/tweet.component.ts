import {Component, Input, OnInit} from '@angular/core';
import {Tweet} from "../../domain/tweet";
import {TweetService} from "../../service/tweet.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {

  @Input() tweetID: Number;
  tweet : Tweet;

  constructor(private tweetService: TweetService, private route: ActivatedRoute) { }

  ngOnInit() {
    if(!this.tweet)
      this.tweetID = +this.route.snapshot.paramMap.get('id');
    this.getTweet();
  }

  getTweet() {
    this.tweetService.getTweet(this.tweetID).subscribe(
      tweet => this.tweet = tweet
    );
  }

}
