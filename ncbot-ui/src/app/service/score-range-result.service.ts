import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ScoreRangeResult} from "../model/score-range-result";

@Injectable({
  providedIn: 'root'
})
export class ScoreRangeResultService {

  constructor(private httpClient:HttpClient) { }

  getAllScoreRangeResults() {
    return this.httpClient.get("/score/ranges");
  }

  uploadImageForScoreRange(scoreRangeResult: ScoreRangeResult) {
    let url = "score/ranges/";
    return this.httpClient.post(url, scoreRangeResult);
  }
}
