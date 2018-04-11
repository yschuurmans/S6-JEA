import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  username: string;
  password: string;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }


  login() {
    this.authService.authenticate(this.username, this.password).subscribe(
      x => {
        this.authService.setToken(x.headers.get('Authorization'))
      });

    this.router.navigate(['/']);

  }
}
