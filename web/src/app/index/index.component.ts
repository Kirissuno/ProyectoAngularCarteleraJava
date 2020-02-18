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

  //Cargamos el array de los juegos que están por venir, estos se mostrarán en la diapositiva del index.
  getUpcommingGames(){
    this.videogameService.getUpcommingGames().subscribe( (data) =>{
      this.upcomingVideogames = data;
    } )
  }

  //Cargamos los últimos 3 objetos de comentarios para mostrarlos en el index.
  getLast3Comments(){
    this.commentService.getLast3Comments().subscribe( (data) =>{
      this.lastComments = data;
    } )
  }

  //Cargamos los últimos juegos lanzados para mostrarlos en la parte inferior del index (estos estarán capados a 5 elementos).
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
