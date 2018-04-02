import { Component, OnInit } from '@angular/core';
import {User} from "../domain/user";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users : User[];
  constructor(private userService : UserService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      users => this.users = users
    );
  }

}
