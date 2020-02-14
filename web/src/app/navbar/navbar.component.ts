import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logged: boolean;

  isMod : boolean = false;

  email: string;
  password : string;

  constructor(private loginService : LoginService, private modalService:NgbModal, private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(form : NgForm){
    if(form.valid){
      this.loginService.logIn(this.email).subscribe( user => {
        if(user.contrasena == this.password){
          this.loginService.logged = true;
          this.logged = this.loginService.logged;
          this.closeModal();
        }
      })
    }
    
  }

  logout(){
    this.password = "";
    this.email = "";
    this.loginService.logged = false;
    this.logged = this.loginService.logged;
    this.router.navigate(["/"])
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
