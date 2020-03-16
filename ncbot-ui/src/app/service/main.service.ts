import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MessageToUsers} from "../model/message-to-users";

@Injectable({
  providedIn: 'root'
})
export class MainService {

  constructor(private httpClient:HttpClient) { }

  sendMessageToUsers(messageToUsers:MessageToUsers):Observable<any> {
    let url:string = "send/global/message";
    return this.httpClient.post(url, messageToUsers);
  }
}
