import { Component, OnInit } from '@angular/core';
import {BotService} from "../service/bot.service";
import {Bot} from "../model/bot/bot";

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {
  bot:Bot = {
    active:true
  };
  constructor(private botService:BotService) { }

  ngOnInit(): void {
    this.botService.isBotActive().subscribe((result:Bot) =>{
      this.bot = result;
    })
  }

  onClickTurnBtn(){
    this.botService.setBotTurn(this.bot).subscribe((result:Bot) =>{
      this.bot = result;
      location.reload();
    });
  }

  checkBotActivity():string{
    if (this.bot.active == true) {
      return "on";
    }
    return "off";
  }

}
