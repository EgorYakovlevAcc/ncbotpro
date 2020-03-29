import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageFileService {

  constructor(private httpClient: HttpClient) { }

  uploadImage(questionId: number, file: File):Observable<any> {
    alert("UPLOAD IMAGE!");
    const formData = new FormData();
    formData.append("image", file);
    let url = "questions/attachment/upload/" + questionId;
    return this.httpClient.post(url, formData);
  }
}
