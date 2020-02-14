import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseURL = "http://localhost:8080/games/user";

  logged : boolean = true;
  userLogged : string = "";

  constructor(private http : HttpClient) { }

  logIn(username : string) : Observable<any>{
    return this.http.get(`${this.baseURL}/${username}`);
  }


}
