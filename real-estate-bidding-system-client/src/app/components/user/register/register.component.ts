import {Component, OnInit} from '@angular/core';
import {RegisterRequestModel} from '../../../core/model/request/user/register-request.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private register: RegisterRequestModel;

  constructor() {
  }

  ngOnInit() {
    this.register = new RegisterRequestModel('', '', '', '', '', '', '');
  }

  onRegister() {
    console.log('reg');
  }
}
