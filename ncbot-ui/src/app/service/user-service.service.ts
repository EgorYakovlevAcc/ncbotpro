import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<any> {
    return this.httpClient.get("all/users");
  }

  givePresentToUser(id): Observable<any> {
    let url = "user/" + id + "/present";
    return this.httpClient.post(url, null);
  }

  isPresenGivenToUser(id): any {
    let url = "user/" + id + "/present";
    return this.httpClient.get(url);
  }
}
