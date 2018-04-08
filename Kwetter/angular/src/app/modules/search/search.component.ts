import { Component, OnInit } from '@angular/core';
import {TweetService} from "../../service/tweet.service";
import {Tweet} from "../../domain/tweet";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchText : String;
  tweets : Tweet[] = [];

  constructor(private tweetService: TweetService) { }

  ngOnInit() {
  }



  changeSearchString() {
    if(this.searchText.length >= 3) {
      this.tweetService.searchTweets(this.searchText).subscribe(
        tweets => this.tweets = tweets
      );
      this.tweetService.searchTweets(this.searchText)
    }
    else {
      this.tweets = [];
    }
  }

}
