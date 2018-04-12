import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Observable} from "rxjs/Observable";
import {Tweet} from "../domain/tweet";
import {HttpClient, HttpHeaders} from "@angular/common/http";


const helper = new JwtHelperService();

@Injectable()
export class AuthService {


  constructor(private http: HttpClient) {
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();
    // return a boolean reflecting
    // whether or not the token is expired
    return !helper.isTokenExpired(token);
  }

  public setToken(token : string, username : string) {
    localStorage.setItem('username', username);
    localStorage.setItem('token', token);
  }

  authenticate(username: String, password: String) {

    return this.http.post<any>(
      'http://localhost:8080/Kwetter/api/login/',
      `username=${username}&password=${password}`,
      {
      observe:"response",
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    });
  }
}
