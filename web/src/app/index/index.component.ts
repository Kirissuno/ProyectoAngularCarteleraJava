import { Component, OnInit } from '@angular/core';
import { VideogameServiceService } from '../services/videogame-service.service';
import { Videogame } from '../models/videogame';
import { CommentServiceService } from '../services/comment-service.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  upcomingVideogames : Videogame[];
  lastComments : Comment[];
  constructor(
    private videogameService : VideogameServiceService,
    private commentService : CommentServiceService
  ) { }

  ngOnInit(): void {
    this.getUpcommingGames();
    this.getLast3Comments();
  }

  getUpcommingGames(){
    this.videogameService.getUpcommingGames().subscribe( (data) =>{
      this.upcomingVideogames = data;
    } )
  }

  getLast3Comments(){
    this.commentService.getLast3Comments().subscribe( (data) =>{
      console.log(data)
      this.lastComments = data;
    } )
  }

}
