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

  //Método del login
  onSubmit(form: NgForm) {
    //Comprueba que el formulario sea válido por la parte del html.
    if (form.valid) {
      //Preguntamos a la base de datos si dicho usuario existe
      this.loginService.logIn(this.email).subscribe(user => {
        //Si el usuario existe
        if (user != null) {
          //Y la contraseña es igual que la de la base de datos
          if (user.contrasena == this.password) {
            //Se guardará en la variable de usuario logeado el usuario con el que se ha hecho el login
            this.loginService.user = user;
            //Si el rol del usuario logeado es admin
            if (user.rol == 'admin') {
              //Se guarda en una variable local booleana verdadera para así desde el HTML controlar cosas que solo pueden hacer los admins.
              this.isAdmin = true;
            }
            //Cambiamos la variable de logeado del servicio del login.
            this.loginService.logged = true;
            this.logged = this.loginService.logged;
            //Cerramos modales
            this.closeModal();
            //Navegamos al index de la aplicación.
            this.router.navigate(["/"])
            //Si el usuario y la contraseña no coinciden, salta error de login.
          } else {
            this.errorLogin = true;
            setTimeout(() => {
              this.errorLogin = false;
            }, 2000);
          }
          //Si el usuario no existe salta error de login 
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

  //Método de registro
  onSubmitRegister(form: NgForm) {
    //Se crea un nuevo objeto de tipo usuario con los valores de los input del HTML y con el rol user
    const newUser: User = {
      usuario: form.value.user,
      contrasena: form.value.password,
      rol: 'user'
    }
    //Comprobamos que dicho usuario que vamos a crear no existe en la base de datos.
    this.userService.getUser(newUser.usuario).subscribe((user) => {
      //Si no existe, lo creamos
      if (user == null) {
        this.userService.addUser(newUser).subscribe(() => {
          //cerramos todos los modales
          this.modalService.dismissAll();
          //vacíamos los campos del email y contraseña
          this.email = "";
          this.password = "";
        })
        //si ya existe en la base de datos saltará un mensaje de error
      } else {
        this.errorRegister = true;
        setTimeout(() => {
          this.errorRegister = false;
        }, 2000);
      }
    })
  }

  //Método de logout
  logout() {
    //al deslogearse vacíamos los campos y volvemos las variables de login vacías.
    this.password = "";
    this.email = "";
    this.loginService.logged = false;
    this.loginService.user = null;
    this.isAdmin = false;
    this.logged = this.loginService.logged;
    //redirigimos al inicio
    this.router.navigate(["/"])
  }

  //Método para abrir modal de login
  loginModal(modal) {
    this.closeModal();
    this.modalService.open(modal);
  }

  //Método para abrir modal de registro
  modalRegister(modal) {
    this.closeModal();
    this.modalService.open(modal);
  }

  //Método para cerrar todos los modales
  closeModal() {
    this.modalService.dismissAll();
  }

}
