import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';
import { User } from '../models/user';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  otherUsers: User[];
  adminUsers: User[];

  modifiedUser: User;

  rol;


  constructor(private userService: UserServiceService, private modalService: NgbModal) {
    this.otherUsers = [];
    this.adminUsers = [];
  }

  ngOnInit(): void {
    this.otherUsers = [];
    this.adminUsers = [];
    //Cargamos todos los usuarios de la base de datos
    this.userService.getAllUsers().subscribe(users => {
      users.forEach(user => {
        //Comprobamos si son admins o no y los guardamos en un array diferente si así es.
        if (user.rol == 'admin') {
          this.adminUsers.push(user);
        } else {
          this.otherUsers.push(user);
        }
      });
      //Ordenamos los 2 arrays.
      this.adminUsers.sort((a, b) => {
        return a.usuario > b.usuario ? 1 : a.usuario < b.usuario ? -1 : 0;
      })
      this.otherUsers.sort((a, b) => {
        return a.usuario > b.usuario ? 1 : a.usuario < b.usuario ? -1 : 0;
      })
    })
    this.rol = { options: "" }
  }

  //Método para cambiar el rol del usuario escogido desde el HTML.
  onSubmit(form: NgForm) {
    if (form.value.options == 1) {
      this.modifiedUser.rol = 'user';
    } else if (form.value.options == 2) {
      this.modifiedUser.rol = 'admin';
    }
    this.userService.updateRol(this.modifiedUser.usuario, this.modifiedUser).subscribe(() => {
      this.modalService.dismissAll();
      this.ngOnInit();
    })
  }

  //Método de borrado de usuario.
  deleteUser(user){
    this.userService.deleteUser(user).subscribe( () =>{
      this.ngOnInit();
    } )
  }

  //Método que cargará el rol por defecto del usuario seleccionado en el HTML
  rolAssign(user) {
    this.rol = { options: "" };
    this.userService.getUser(user).subscribe(user => {
      this.modifiedUser = user;
      if (user.rol == 'user') {
        this.rol = { options: '1' }
      } else if (user.rol == 'admin') {
        this.rol = { options: '2' }
      }
    })
  }

  //Método que abrirá modal de modificación
  modalModify(modal) {
    this.modalService.open(modal);
  }

  //Método que cerrará todos los modales.
  closeModal() {
    this.modalService.dismissAll();
  }

}
