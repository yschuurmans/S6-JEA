import { Component } from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {AuthService} from "./auth/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  currentUser : string;
  loggedIn : boolean;

  constructor(private authService : AuthService){}

  ngOnInit() {
    this.refreshLogout();
  }

  ngAfterViewInit() {
    this.refreshLogout();
  }

  refreshLogout() {
    this.loggedIn = this.authService.isAuthenticated();
    this.currentUser = localStorage.getItem('username');

  }

  logout() {
    localStorage.setItem('token', '');
    localStorage.setItem('username', '');
    location.reload();
  }
}


