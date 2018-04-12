import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {User} from "../../domain/user";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @Input() username: String;
  user : User;

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    if(!this.username || this.username.length < 1)
      this.username = localStorage.getItem('username');
    this.getUser();
  }

  getUser() {
    this.userService.getUser(this.username).subscribe(
      user => this.user = user
    );
  }
}
