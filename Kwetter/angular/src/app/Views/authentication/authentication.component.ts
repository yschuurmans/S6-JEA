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
  //    resp => console.log(resp.headers));
      x => {
        console.log(x);
        console.log(x.headers);
        console.log(x.headers.get('Authorization'));
        this.authService.setToken(x.headers.get('Authorization'), this.username);
        this.router.navigate(['/'])
      });
  }
}
