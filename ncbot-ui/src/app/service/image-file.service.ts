import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageFileService {

  constructor(private httpClient: HttpClient) { }

  uploadImage(questionId: number, file: File):Observable<any> {
    const formData = new FormData();
    formData.append("image", file);
    let url = "/attachment/upload/" + questionId;
    return this.httpClient.post(url, formData);
  }
}
