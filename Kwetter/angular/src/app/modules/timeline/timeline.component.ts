import {Component, Input, OnInit} from '@angular/core';
import {TweetService} from "../../service/tweet.service";
import {Tweet} from "../../domain/tweet";
import {SocketService} from "../../service/socket.service";

@Component({
  selector: 'app-timeline',
  templateUrl: './timeline.component.html',
  styleUrls: ['./timeline.component.css']
})
export class TimelineComponent implements OnInit {
  tweets : Tweet[] = [];
  @Input() username: string;

  constructor(private tweetService: TweetService, private socketService : SocketService) { }

  ngOnInit() {
    this.refreshTweets();
    this.subscribeWebsocket();
  }

  refreshTweets() {
    console.log("refreshing tweets");
    if(this.username.length < 1) this.username = 's';
    this.getTimelineTweets(this.username);
  }

  getTimelineTweets(userID : String) : void {
    this.tweetService.getTimelineTweets(userID).subscribe(
      tweets => this.tweets = tweets
    );
  }

  subscribeWebsocket() {
    this.socketService.subscribeTimeline(this.username).subscribe(
      message => {
        console.log("RECEIVED : ");
        console.log(message.data);
        console.log(JSON.parse(message.data));
        this.tweets.unshift( JSON.parse(message.data));
        console.log(this.tweets);
      }
    );
  }

}
