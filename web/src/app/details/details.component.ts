import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Videogame } from '../models/videogame';
import { VideogameServiceService } from '../services/videogame-service.service';
import { CommentServiceService } from '../services/comment-service.service';
import { User } from '../models/user';
import { LoginService } from '../services/login.service';
import { Comment } from '../models/comment';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  gameTitle : string;
  comments : Comment[];
  game : Videogame;

  loggedUser : User;
  loggedUsername : string;

  newComment: string;

  isUser: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private videogameService : VideogameServiceService,
    private commentService : CommentServiceService,
    private loginSerice : LoginService
  ) {
    this.comments = [];
  }

  ngOnInit(): void {
    if(this.loginSerice.user != null){
      this.loggedUser = this.loginSerice.user;
      if(this.loggedUser.rol == 'user'){
        this.isUser = true;
      }else{
        this.isAdmin = true;
      }
    }

    this.comments = [];
    this.gameTitle = this.route.snapshot.url[1].path;
    this.videogameService.getByTitle(this.gameTitle).subscribe( gameDB => {
      if(gameDB != null || gameDB != undefined){
        this.game = gameDB;
      }
    } )
    this.getAllCommentsByTitle();
    
  }

  getAllCommentsByTitle(){
    this.commentService.getByGameTitle(this.gameTitle).subscribe( comms => {
      this.comments = comms;
    } )
  }

  addComment(comment:string){
    const newComment : Comment = {
      id : null,
      titulo : this.gameTitle,
      comentario : comment,
      usuario : this.loggedUser.usuario
    }
    this.commentService.addComment(newComment).subscribe( () => {
      this.getAllCommentsByTitle();
      comment = "";
    });
  }

  deleteComment(id){
    this.commentService.deleteComment(id).subscribe( () => {
      this.getAllCommentsByTitle();
    } )
  }

}
