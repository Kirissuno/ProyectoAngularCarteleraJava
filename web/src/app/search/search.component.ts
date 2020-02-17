import { Component, OnInit } from '@angular/core';
import { Videogame } from '../models/videogame';
import { VideogameServiceService } from '../services/videogame-service.service';
import { LoginService } from '../services/login.service';
import { User } from '../models/user';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  videogames : Videogame[];
  gameToFilter : string;
  noResult : boolean = false;
  videogamesFiltered : Videogame[];

  logedUser : User;
  isAdmin : boolean = false;

  newGameForm : FormGroup;

  errorNew : boolean = false;

  constructor(
    private videogameService : VideogameServiceService,
    private loginService : LoginService,
    private modalService : NgbModal,
  ) {
    this.videogames = [];
    this.videogamesFiltered = [];
    this.noResult = false;
    this.newGameForm = new FormGroup({
      'title':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'company':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'description':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'imageURL':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255), Validators.pattern('(https?:\/\/.*\.(?:png|jpg))')]),
      'releaseDate':new FormControl('',  [Validators.required])
    })
  }

  onSubmitNew(correcto){
    console.log(this.newGameForm.value);
    const newGame :Videogame = {
      director : this.newGameForm.value.company.trim(),
      titulo : this.newGameForm.value.title.trim(),
      descripcion : this.newGameForm.value.description.trim(),
      urlImage : this.newGameForm.value.imageURL.trim(),
      fecha : this.newGameForm.value.releaseDate.trim(),
    }
    
    this.videogameService.addGame(newGame).subscribe( () =>{
      this.modalService.dismissAll();
      this.ngOnInit();
      this.modalService.open(correcto);
    }, () =>{
      this.errorNew = true;
      setTimeout(() => {
        this.errorNew = false;
      }, 3000);
    } )
  }

  ngOnInit(): void {
    this.videogameService.getAllGames().subscribe( games => {
      this.videogames = games;
    })
    this.noResult = false;
    this.videogamesFiltered = [];
    this.logedUser = this.loginService.user;
    if(this.logedUser != null || this.logedUser.rol != undefined){
      if(this.logedUser.rol == 'admin'){
        this.isAdmin = true;
      }
    }    

  }

  addGame(modalAdd){
    this.modalService.open(modalAdd, {size:'md'});
  }

  deleteGame(title){
    this.videogameService.deleteGame(title).subscribe(() =>{
      this.ngOnInit();
    })
  }

  change(){
    this.videogamesFiltered = [];
    if(this.gameToFilter.trim() != "" || this.gameToFilter != null || this.gameToFilter != undefined){
      this.videogameService.getAllGames().subscribe( games => {
        games.forEach(game => {
          if(game.titulo.trim().toLocaleLowerCase().includes(this.gameToFilter) || game.director.trim().toLocaleLowerCase().includes(this.gameToFilter) ){
            this.videogamesFiltered.push(game);
          }
        });
        
        if(this.videogamesFiltered.length == 0){
          this.noResult = true;
          setTimeout(() => {
            this.noResult = false;
          }, 2000);
        }
      })
    }    
  }

  closeModals(){
    this.modalService.dismissAll();
  }

}
