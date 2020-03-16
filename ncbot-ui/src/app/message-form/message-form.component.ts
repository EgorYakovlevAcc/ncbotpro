import {Component, OnInit} from '@angular/core';
import {MessageToUsers} from "../model/message-to-users";
import {MainService} from "../service/main.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {
  messageToUsers: MessageToUsers = {
    text: '',
    maxScore:0,
    minScore:0
  };

  constructor(private mainService: MainService) {
  }

  ngOnInit(): void {
  }

  sendMessageToUsers() {
    this.mainService.sendMessageToUsers(this.messageToUsers).subscribe(result => {
        window.location.href = "/";
      },
      error => {
        alert("Error during sending the message");
      })
  }
}
