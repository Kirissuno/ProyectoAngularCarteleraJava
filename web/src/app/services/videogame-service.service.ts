import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VideogameServiceService {

  private URLBase : string = "http://localhost:8080/game";

  constructor( private http : HttpClient) { }

  getAllGames(): Observable<any>{
    return this.http.get(`${this.URLBase}s`);
  }

  getByTitle(title : string): Observable<any>{
    return this.http.get(`${this.URLBase}s/title/${title}`)
  }

  getGamesByCompany(company : string): Observable<any>{
    return this.http.get(`${this.URLBase}s/director/${company}`)
  }

  getUpcommingGames(): Observable<any>{
    return this.http.get(`${this.URLBase}s/upcomming`);
  }

  getLastGames(): Observable<any>{
    return this.http.get(`${this.URLBase}s/last`);
  }
}
