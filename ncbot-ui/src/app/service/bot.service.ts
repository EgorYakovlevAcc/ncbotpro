import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bot} from "../model/bot/bot";

@Injectable({
  providedIn: 'root'
})
export class BotService {

  constructor(private httpClient: HttpClient) { }

  isBotActive():Observable<any> {
    return this.httpClient.get("/bot/get/turn");
  }

  setBotTurn(bot:Bot):any {
    let url:string = "/bot/turn";
    return this.httpClient.post(url, bot);
  }
}
