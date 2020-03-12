import {Component, OnInit} from '@angular/core';
import {UserServiceService} from "../service/user-service.service";
import {User} from "../model/user/user";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[];

  constructor(private userService: UserServiceService) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((result: User[]) => {
        this.users = result;
        this.checkIsUserActiveNow(this.users);
      }
    )
  }

  checkIsUserActiveNow(users): void {
    users.forEach(function (value: User) {
      if (value.activeNow) {
        value.color = "yellow";
      } else {
        value.color = "white";
      }
    })
  }

  checkIsPresentGivenToUser(users) : void {
    users.forEach(function (value:User) {

    })
  }

  givePresentToUser(id): void {
    this.userService.givePresentToUser(id).subscribe((result:User) => {
      result.status = "disable";
      location.reload();
    })
  }

}
