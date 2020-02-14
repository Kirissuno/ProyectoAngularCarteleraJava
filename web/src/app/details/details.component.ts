import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Videogame } from '../models/videogame';
import { VideogameServiceService } from '../services/videogame-service.service';
import { CommentServiceService } from '../services/comment-service.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  gameTitle : string;
  comments : Comment[];
  game : Videogame;

  constructor(
    private route: ActivatedRoute,
    private videogameService : VideogameServiceService,
    private commentService : CommentServiceService
  ) {
    this.comments = [];
  }

  ngOnInit(): void {
    this.comments = [];
    this.gameTitle = this.route.snapshot.url[1].path;
    this.videogameService.getByTitle(this.gameTitle).subscribe( gameDB => {
      if(gameDB != null || gameDB != undefined){
        this.game = gameDB;
      }
    } )
    this.commentService.getByGameTitle(this.gameTitle).subscribe( comms => {
      this.comments = comms;
    } )
  }

}
