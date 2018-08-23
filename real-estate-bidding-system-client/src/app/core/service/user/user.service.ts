import {Injectable} from '@angular/core';
import {HttpClientService} from '../http-client.service';
import {RegisterRequestModel} from '../../model/request/user/register-request.model';
import {LoginRequestModel} from '../../model/request/user/login-request.model';
import {UserResponseModel} from '../../model/response/user/user-response.model';
import {UserProfileResponseModel} from '../../model/response/user/user-profile-response.model';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {EstateViewModel} from '../../model/response/estate/estate-view.model';

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
  readonly GET_USER_BY_USERNAME_URL = 'http://localhost:8080/users/';
  readonly HOME_URL = '/';
  readonly SIGNED_OUT_SUCCESSFULLY_MESSAGE = 'Signed out successfully.';
  readonly SUCCESS = 'Success.';

  private _authToken: string;
  private _isAdmin: string;

  constructor(private httpClient: HttpClientService, private router: Router, private toastr: ToastrService) {
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

  getUserById(id: number) {
    return this.httpClient.get<UserProfileResponseModel>(this.GET_USER_BY_USERNAME_URL + id);
  }

  getCurrentlyLoggedInUser() {
    return this.httpClient.get<UserResponseModel>(this.CURRENT_USER_URL);
  }

  getCurrentlyLoggedInUserForProfile() {
    return this.httpClient.get<UserProfileResponseModel>(this.CURRENT_USER_URL);
  }

  getUserEstates(userId: number) {
    const USER_ESTATES_URL = `http://localhost:8080/users/${userId}/estates`;
    return this.httpClient.get<EstateViewModel[]>(USER_ESTATES_URL);
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

  logout() {
    this.authToken = undefined;
    localStorage.clear();
    this.toastr.info(this.SIGNED_OUT_SUCCESSFULLY_MESSAGE, this.SUCCESS);
    this.router.navigate([this.HOME_URL]);
  }
}
