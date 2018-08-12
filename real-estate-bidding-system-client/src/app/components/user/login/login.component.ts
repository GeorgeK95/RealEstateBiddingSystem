import {Component, OnInit} from '@angular/core';
import {LoginRequestModel} from '../../../core/model/request/user/login-request.model';
import {Router} from '@angular/router';
import {UserService} from '../../../core/service/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private login: LoginRequestModel;
  private HOME_URL: '/';

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.login = new LoginRequestModel('', '');
  }

  onLogin() {
    this.userService.login(this.login).subscribe();
  }
}
