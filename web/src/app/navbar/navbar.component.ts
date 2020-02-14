import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logged: boolean;

  constructor(private loginService : LoginService, private modalService:NgbModal) { }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log("form enviado")
    this.logged = true;
    this.closeModal();
  }

  logout(){
    this.logged = false;
  }

  loginModal(modal){
    this.closeModal();
    this.modalService.open(modal);
  }

  modalRegister(modal){
    this.closeModal();
    this.modalService.open(modal);
  }

  closeModal(){
    this.modalService.dismissAll();
  }

}
