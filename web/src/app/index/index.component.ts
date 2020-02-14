import { Component, OnInit } from '@angular/core';
import { VideogameServiceService } from '../services/videogame-service.service';
import { Videogame } from '../models/videogame';
import { Comment } from '@angular/compiler';
import { CommentServiceService } from '../services/comment-service.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  upcomingVideogames : Videogame[];
  lastComments : Comment[];
  latestGames : Videogame[]; 

  constructor(
    private videogameService : VideogameServiceService,
    private commentService : CommentServiceService
  ) { 
    
  }

  ngOnInit(): void {
    this.latestGames = [];
    this.getUpcommingGames();
    this.getLast3Comments();
    this.getLatestGames();
  }

  getUpcommingGames(){
    this.videogameService.getUpcommingGames().subscribe( (data) =>{
      this.upcomingVideogames = data;
    } )
  }

  getLast3Comments(){
    this.commentService.getLast3Comments().subscribe( (data) =>{
      this.lastComments = data;
    } )
  }

  getLatestGames(){
    this.videogameService.getLastGames().subscribe( (data) =>{
      data.forEach(element => {
        if(this.latestGames.length < 5){
          this.latestGames.push(element);
        }
      });
    } )
  }

}
