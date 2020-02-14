import { Component, OnInit } from '@angular/core';
import { Videogame } from '../models/videogame';
import { VideogameServiceService } from '../services/videogame-service.service';

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

  constructor(
    private videogameService : VideogameServiceService,
  ) {
    this.videogames = [];
    this.videogamesFiltered = [];
    this.noResult = false;
  }

  ngOnInit(): void {
    this.videogameService.getAllGames().subscribe( games => {
      this.videogames = games;
    })
    this.noResult = false;
    this.videogamesFiltered = []
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

}
