import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Tweet} from "../domain/tweet";
import {User} from "../domain/user";

@Injectable()
export class TweetService {
  private tweetsUrl = 'http://localhost:8080/Kwetter/api/tweets';  // URL to web api
  constructor(private http: HttpClient) {
  }

  postTweet(username: String, tweet: Tweet): Observable<Tweet[]> {
    return this.http.put<Tweet[]>('http://localhost:8080/Kwetter/api/users/' + username + '/tweets', tweet);
  }

  getAllTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.tweetsUrl)
  }

  getTimelineTweets(username: String): Observable<Tweet[]> {
    return this.http.get<Tweet[]>('http://localhost:8080/Kwetter/api/users/' + username + '/timeline');
  }

  searchTweets(searchString: String): Observable<Tweet[]> {
    return this.http.get<Tweet[]>('http://localhost:8080/Kwetter/api/tweets/search?searchString=' + searchString);
  }

  getTweet(tweetID: Number) {
    return this.http.get<Tweet>('http://localhost:8080/Kwetter/api/tweets/' + tweetID);
  }
}
