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

  constructor() {
  }

  ngOnInit() {
  }
}


