import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MessageToUsers} from "../model/message-to-users";

@Injectable({
  providedIn: 'root'
})
export class MainService {

  constructor(private httpClient:HttpClient) { }

  sendMessageToUsers(messageToUser:MessageToUsers):Observable<any> {
    let url:string = "/global/message";
    return this.httpClient.post(url, messageToUser);
  }
}
