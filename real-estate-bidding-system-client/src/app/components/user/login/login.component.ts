import {Component, OnInit} from '@angular/core';
import {LoginRequestModel} from '../../../core/model/request/user/login-request.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private login: LoginRequestModel;

  constructor() {
  }

  ngOnInit() {
    this.login = new LoginRequestModel('', '');
  }

  onLogin() {
    console.log('logg');
  }
}
