import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { User } from '../models/user';
import { DetailsComponent } from '../details/details.component';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logged: boolean;

  isAdmin: boolean = false;

  email: string;
  password: string;

  errorLogin: boolean = false;
  errorRegister: boolean = false;

  constructor(private loginService: LoginService,
    private modalService: NgbModal,
    private router: Router,
    private userService: UserServiceService,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.loginService.logIn(this.email).subscribe(user => {
        if (user != null) {
          if (user.contrasena == this.password) {
            this.loginService.user = user;
            if (user.rol == 'admin') {
              this.isAdmin = true;
            }
            this.loginService.logged = true;
            this.logged = this.loginService.logged;
            this.closeModal();
            this.router.navigate(["/"])
          }
        } else {
          this.errorLogin = true;
          setTimeout(() => {
            this.errorLogin = false;
          }, 2000);
        }

      }, (err) => {
        this.errorLogin = true;
        setTimeout(() => {
          this.errorLogin = false;
        }, 2000);
      })
    }
  }

  onSubmitRegister(form: NgForm) {
    const newUser: User = {
      usuario: form.value.user,
      contrasena: form.value.password,
      rol: 'user'
    }
    console.log(newUser)
    this.userService.addUser(newUser).subscribe(() => {
      this.modalService.dismissAll();
      this.email = "";
      this.password = "";
    }, () => {
      this.errorRegister = true;
      setTimeout(() => {
        this.errorRegister = false;
      }, 2000);
    })
  }

  logout() {
    this.password = "";
    this.email = "";
    this.loginService.logged = false;
    this.loginService.user = null;
    this.isAdmin = false;

    this.logged = this.loginService.logged;
    this.router.navigate(["/"])
  }

  loginModal(modal) {
    this.closeModal();
    this.modalService.open(modal);
  }

  modalRegister(modal) {
    this.closeModal();
    this.modalService.open(modal);
  }

  closeModal() {
    this.modalService.dismissAll();
  }

}
