import {Component, OnInit} from '@angular/core';
import {RegisterRequestModel} from '../../../core/model/request/user/register-request.model';
import {UserService} from '../../../core/service/user/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private register: RegisterRequestModel;
  private HOME_URL: '/';

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.register = new RegisterRequestModel('', '', '', '', '', '', '');
  }

  onRegister() {
    this.userService.register(this.register).subscribe();
  }
}
