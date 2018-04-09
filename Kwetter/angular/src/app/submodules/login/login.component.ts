import { Component, OnInit } from '@angular/core';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username : string;
  password : string;

  constructor(private cookieService : CookieService) {

  }

  ngOnInit() {
  }

  login() {
    this.cookieService.set('username', this.username);
    console.log("User is now: " + this.cookieService.get('username'))
    location.reload()

  }
}
