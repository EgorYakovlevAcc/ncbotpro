import { Component, OnInit } from '@angular/core';
import {BotService} from "../service/bot.service";
import {Bot} from "../model/bot/bot";

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {
  bot:Bot;
  constructor(private botService:BotService) { }

  ngOnInit(): void {
    this.botService.isBotActive().subscribe((result:Bot) =>{
      this.bot = result;
    })
  }

  onClickTurnBtn(){
    if (this.bot.isActive) {
      this.bot.isActive = false;
    }
    else {
      this.bot.isActive = true;
    }
    this.botService.setBotTurn(this.bot).subscribe(result =>{
      location.reload();
    });
  }

}
