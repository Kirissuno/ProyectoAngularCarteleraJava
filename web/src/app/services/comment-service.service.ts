import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  private URLBase : string = "http://localhost:8080/game/comment";
  private URLBases : string = "http://localhost:8080/games/comments";

  constructor( private http: HttpClient) { }

  getAllComments(): Observable<any>{
    return this.http.get(`${this.URLBases}`);
  }

  getLast3Comments(): Observable<any>{
    return this.http.get(`${this.URLBases}/last3`);
  }
}