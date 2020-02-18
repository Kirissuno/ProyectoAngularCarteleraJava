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

  //Iniciamos las variables de para obtener el rol del usuario admin/user y cargamos el juego de la base de datos en una variable local.
  ngOnInit(): void {
    if(this.loginSerice.user != null){
      this.loggedUser = this.loginSerice.user;
      if(this.loggedUser.rol == 'user'){
        this.isUser = true;
      }else if(this.loggedUser.rol == 'admin'){
        this.isAdmin = true;
      }
    }

    this.comments = [];
    this.gameTitle = this.route.snapshot.url[1].path;
    this.videogameService.getByTitle(this.gameTitle).subscribe( gameDB => {
      if(gameDB != null || gameDB != undefined){
        this.game = gameDB;
      }
    })
    this.getAllCommentsByTitle();
    
  }

  //Método para obtener todos los comentarios de un videojuego, por su título.
  getAllCommentsByTitle(){
    this.commentService.getByGameTitle(this.gameTitle).subscribe( comms => {
      this.comments = comms;
    } )
  }

  //Método que crea un nuevo objeto de tipo comentario que será añadido a la base de datos.
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

  //Método para borrar un comentario
  deleteComment(id){
    this.commentService.deleteComment(id).subscribe( () => {
      this.getAllCommentsByTitle();
    } )
  }

}
