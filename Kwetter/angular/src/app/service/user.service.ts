import { Injectable } from '@angular/core';
import {User} from "../domain/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {
  private usersUrl = 'http://localhost:8080/Kwetter/api/users';  // URL to web api
  constructor(
    private http: HttpClient) { }

  /** GET hero by id. Will 404 if id not found */
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  getFollowers(username: String) {
    return this.http.get<User[]>(this.usersUrl + '/'+username+'/followers');
  }

  getFollowing(username: String) {
    return this.http.get<User[]>(this.usersUrl + '/'+username+'/following');
  }

  getUser(username: String) {
    return this.http.get<User>(this.usersUrl + '/'+username);
  }

  updateUser(username: String, user : User) {
    return this.http.post<User>(this.usersUrl + '/'+username, user);
  }
}
