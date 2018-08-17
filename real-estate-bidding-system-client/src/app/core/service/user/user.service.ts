import {Injectable} from '@angular/core';
import {HttpClientService} from '../http-client.service';
import {RegisterRequestModel} from '../../model/request/user/register-request.model';
import {LoginRequestModel} from '../../model/request/user/login-request.model';
import {UserResponseModel} from '../../model/response/user-response.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly AUTH_TOKEN = 'authtoken';
  readonly IS_ADMIN = 'is_admin';
  readonly REGISTER_URL = `http://localhost:8080/users/register`;
  readonly LOGIN_URL = 'http://localhost:8080/users/login';
  readonly ALL_USERS_URL = 'http://localhost:8080/admin/users';
  readonly CURRENT_USER_URL = 'http://localhost:8080/users/current';
  readonly TRUE = 'true';
  // readonly GET_USER_BY_USERNAME_URL = 'http://localhost:8080/users/';

  private _authToken: string;
  private _isAdmin: string;

  constructor(private httpClient: HttpClientService) {
  }

  register(registerModel: RegisterRequestModel) {
    return this.httpClient.post<RegisterRequestModel>(this.REGISTER_URL, registerModel);
  }

  login(loginModel: LoginRequestModel) {
    return this.httpClient.post<LoginRequestModel>(this.LOGIN_URL, loginModel);
  }

  checkIfLoggedIn() {
    return this.authToken === localStorage.getItem(this.AUTH_TOKEN);
  }

  checkIfAdmin() {
    return localStorage.getItem(this.IS_ADMIN) === this.TRUE;
  }

  /*getUserByUsername(username: string) {
    return this.httpClient.get<UserProfileResponseModel>(this.GET_USER_BY_USERNAME_URL + username);
  }*/

  getCurrentlyLoggedInUser() {
    return this.httpClient.get<UserResponseModel>(this.CURRENT_USER_URL);
  }


  get isAdmin(): string {
    return this._isAdmin;
  }

  set isAdmin(value: string) {
    this._isAdmin = value;
  }

  get authToken(): string {
    return this._authToken;
  }

  set authToken(value: string) {
    this._authToken = value;
  }

  getUsers() {
    return this.httpClient.get<UserResponseModel[]>(this.ALL_USERS_URL);
  }
}
