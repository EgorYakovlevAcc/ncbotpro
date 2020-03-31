import { Component, OnInit } from '@angular/core';
import {ScoreRangeResult} from "../model/score-range-result";
import {ScoreRangeResultService} from "../service/score-range-result.service";
import {ImageFileService} from "../service/image-file.service";

@Component({
  selector: 'app-score-range-message',
  templateUrl: './score-range-message.component.html',
  styleUrls: ['./score-range-message.component.css']
})
export class ScoreRangeMessageComponent implements OnInit {
  scoreRanges:ScoreRangeResult[];
  imageFile:File;
  constructor(private scoreRangeResultService: ScoreRangeResultService) { }

  ngOnInit(): void {
    this.scoreRanges = [];
    this.scoreRangeResultService.getAllScoreRangeResults().subscribe((result:ScoreRangeResult) => {
      this.scoreRanges.push(result);
    });
  }

  addNewScoreRange() {
    let scoreRangeResult: ScoreRangeResult = new ScoreRangeResult();
    this.scoreRanges.push(scoreRangeResult);
  }

  scoreRangeResultSend(scoreRangeResult: ScoreRangeResult) {
    scoreRangeResult.image = this.imageFile;
    this.scoreRangeResultService.uploadImageForScoreRange(scoreRangeResult).subscribe(result =>{
      alert("SUCCESS");
    },
      error => {
      alert("ERROR");
      })
  }

  changeImageFile(event) {
    this.imageFile = event.target.files[0];
  }
}
