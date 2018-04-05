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

  /** GET hero by id. Will 404 if id not found */
  getAllTweets(): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(this.tweetsUrl)
  }

  /** GET hero by id. Will 404 if id not found */
  getTimelineTweets(username: String): Observable<Tweet[]> {
    return this.http.get<Tweet[]>('http://localhost:8080/Kwetter/api/users/' + username + '/timeline');
  }
}
