import {Component, OnInit} from '@angular/core';
import {RegisterRequestModel} from '../../../core/model/request/user/register-request.model';
import {UserService} from '../../../core/service/user/user.service';
import {Router} from '@angular/router';
import {CityResponseModel} from '../../../core/model/response/city/city-response.model';
import {EstateService} from '../../../core/service/estate/estate.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private register: RegisterRequestModel;
  private HOME_URL: '/';
  private cities: CityResponseModel[];

  constructor(private estateService: EstateService, private userService: UserService) {
  }

  ngOnInit() {
    this.register = new RegisterRequestModel('', '', '', '', '', '', '');
    this.estateService.getCities()
      .subscribe((res: any) => {
        this.cities = res.body;
      });
  }

  onRegister() {
    this.userService.register(this.register).subscribe();
  }
}
