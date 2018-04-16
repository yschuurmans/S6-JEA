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
  user: User;

  thisUser: boolean;
  editMode: boolean;

  constructor(private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const username = this.route.snapshot.paramMap.get('username');
    if (!username || username.length < 1)
      this.username = localStorage.getItem('username');
    else this.username = username;
    this.getUser();
  }

  getUser() {
    this.userService.getUser(this.username).subscribe(
      user => {
        this.user = user;
        this.thisUser = user.username == localStorage.getItem("username");
      }
    );

  }

  enableEdit() {
    this.editMode = !this.editMode;
  }

  saveEdit() {
    var newUser = new User();

    newUser.username = this.user.username;
    newUser.bio = this.user.bio;

    this.userService.updateUser(this.username, newUser).subscribe(
      () => {
        this.getUser();
      }
    );
  }
}
