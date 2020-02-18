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
    //Creación en el constructor de un formulario reactivo con sus validators para usarlo en la parte del HTML
    this.newGameForm = new FormGroup({
      'title':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'company':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'description':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255)]),
      'imageURL':new FormControl('',  [Validators.required, Validators.minLength(5), Validators.maxLength(255), Validators.pattern('(https?:\/\/.*\.(?:png|jpg))')]),
      'releaseDate':new FormControl('',  [Validators.required])
    })
  }

  //Método para añadir un nuevo juego
  onSubmitNew(correcto){
    //Creamos un objeto de tipo juego con los campos del formulario reactivo.
    const newGame :Videogame = {
      director : this.newGameForm.value.company.trim(),
      titulo : this.newGameForm.value.title.trim(),
      descripcion : this.newGameForm.value.description.trim(),
      urlImage : this.newGameForm.value.imageURL.trim(),
      fecha : this.newGameForm.value.releaseDate.trim(),
    }
    //Comprobamos si ya existe un juego con este título en la base de datos
    this.videogameService.getByTitle(newGame.titulo).subscribe( game => {
      //Si no existe se añadirá
      if(game == null){
        this.videogameService.addGame(newGame).subscribe( (data) =>{
          //se cerrarán todos los modales.
          this.modalService.dismissAll();
          //recargamos el componente
          this.ngOnInit();
          //abrimos modal de inserción correcta
          this.modalService.open(correcto);
        })
        //si ya existe en la base de datos saltará un erros que desaparecerá a los 3 segundos
      }else{
        this.errorNew = true;
        setTimeout(() => {
          this.errorNew = false;
        }, 3000);
      }
    })
  }

  ngOnInit(): void {
    this.videogameService.getAllGames().subscribe( games => {
      this.videogames = games;
    })
    this.noResult = false;
    this.videogamesFiltered = [];
    this.logedUser = this.loginService.user;
    //al igual que en el componente del navbar, comprobamos si estamos logeados como admin para funciones de solo admin.
    if(this.logedUser != null || this.logedUser.rol != undefined){
      if(this.logedUser.rol == 'admin'){
        this.isAdmin = true;
      }
    }    

  }

  //Método para abrir modal para añadir nuevo juego.
  addGame(modalAdd){
    this.modalService.open(modalAdd, {size:'md'});
  }

  //Método para borrar un juego.
  deleteGame(title){
    this.videogameService.deleteGame(title).subscribe(() =>{
      this.ngOnInit();
    })
  }

  //Método de búsqueda
  //Se activa al cambiar el contenido del input del HTML
  change(){
    this.videogamesFiltered = [];
    //Comprobamos que el buscador no esté vacío, esté null o indefinido
    if(this.gameToFilter.trim() != "" || this.gameToFilter != null || this.gameToFilter != undefined){
      //cargamos todos los juegos de la base de datos
      this.videogameService.getAllGames().subscribe( games => {
        //por cada juego
        games.forEach(game => {
          //si su título o companía incluyen la cadena de texto que hemos introducido en el input
          if(game.titulo.trim().toLocaleLowerCase().includes(this.gameToFilter) || game.director.trim().toLocaleLowerCase().includes(this.gameToFilter) ){
            //se añadirá dicho videojuego al nuevo array de filtro
            this.videogamesFiltered.push(game);
          }
        });
        //si el array de filtro está vacío, entonces saldrá un mensaje controlado desde el HTML
        if(this.videogamesFiltered.length == 0){
          this.noResult = true;
          setTimeout(() => {
            this.noResult = false;
          }, 2000);
        }
      })
    }    
  }

  //Método para cerrar todos los modales.
  closeModals(){
    this.modalService.dismissAll();
  }

}
