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
    this.userService.getAllUsers().subscribe(users => {
      users.forEach(user => {
        if (user.rol == 'admin') {
          this.adminUsers.push(user);
        } else {
          this.otherUsers.push(user);
        }
      });
      this.adminUsers.sort((a, b) => {
        return a.usuario > b.usuario ? 1 : a.usuario < b.usuario ? -1 : 0;
      })
      this.otherUsers.sort((a, b) => {
        return a.usuario > b.usuario ? 1 : a.usuario < b.usuario ? -1 : 0;
      })
    })
    this.rol = { options: "" }
  }

  onSubmit(form: NgForm) {
    console.log(form.value.options + " " + this.rol.options)
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

  deleteUser(user){
    this.userService.deleteUser(user).subscribe( () =>{
      this.ngOnInit();
    } )
  }

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

  modalModify(modal) {
    this.modalService.open(modal);
  }

  closeModal() {
    this.modalService.dismissAll();
  }

}
