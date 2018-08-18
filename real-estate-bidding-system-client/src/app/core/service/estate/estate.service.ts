import {Injectable} from '@angular/core';
import {HttpClientService} from '../http-client.service';
import {RegisterRequestModel} from '../../model/request/user/register-request.model';
import {LoginRequestModel} from '../../model/request/user/login-request.model';
import {UserResponseModel} from '../../model/response/user/user-response.model';
import {UserProfileResponseModel} from '../../model/response/user/user-profile-response.model';
import {CityResponseModel} from '../../model/response/city/city-response.model';

@Injectable({
  providedIn: 'root'
})
export class EstateService {
  readonly GET_COUNTRIES_URL = 'http://localhost:8080/auth/cities';
  readonly GET_TYPES_URL = 'http://localhost:8080/auth/types';

  constructor(private httpClient: HttpClientService) {
  }

  getCities() {
    return this.httpClient.get(this.GET_COUNTRIES_URL);
  }

  getTypes() {
    return this.httpClient.get(this.GET_TYPES_URL);
  }
}
