import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Tweet} from "../domain/tweet";

@Injectable()
export class TweetService {
  private heroesUrl = 'api/tweets';  // URL to web api
  constructor(
    private http: HttpClient) { }

  /** GET hero by id. Will 404 if id not found */
  getAllTweets(): Observable<Tweet[]> {
    const url = `${this.heroesUrl}`;
    return this.http.get<Tweet[]>(url)
  }
}
