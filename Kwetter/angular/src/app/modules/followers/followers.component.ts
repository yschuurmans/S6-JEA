import {Component, Input, OnInit} from '@angular/core';
import {Tweet} from "../../domain/tweet";
import {User} from "../../domain/user";
import {TweetService} from "../../service/tweet.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {

  users : User[] = [];
  @Input() username: String;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getFollowers();
  }

  getFollowers() : void {
    this.userService.getFollowers(this.username).subscribe(
      users => this.users = users
    );
  }

  getFollowing() : void {
    this.userService.getFollowing(this.username).subscribe(
      users => this.users = users
    );
  }

}
