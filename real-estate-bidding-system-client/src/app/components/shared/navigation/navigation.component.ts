import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../core/service/user/user.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserResponseModel} from '../../../core/model/response/user/user-response.model';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  readonly HOME_URL = '/';
  readonly SIGNED_OUT_SUCCESSFULLY_MESSAGE = 'Signed out successfully.';
  readonly SUCCESS = 'Success.';
  readonly BTN_CLOSE = 'navbar-toggler collapsed';
  readonly BTN_OPEN = 'navbar-toggler';
  readonly DIV_CLOSE = 'navbar-collapse collapse';
  readonly DIV_OPEN = 'navbar-collapse collapse show';

  private hamburgerBtnClass: string;
  private hamburgerDivClass: string;

  constructor(private userService: UserService, private router: Router, private toastr: ToastrService) {
  }

  ngOnInit() {
    this.hamburgerBtnClass = this.BTN_CLOSE;
    this.hamburgerDivClass = this.DIV_CLOSE;
  }

  isLoggedIn() {
    return this.userService.checkIfLoggedIn();
  }

  isAdmin() {
    return this.userService.checkIfAdmin();
  }

  onLogout() {
    this.userService.authToken = undefined;
    localStorage.clear();
    this.toastr.info(this.SIGNED_OUT_SUCCESSFULLY_MESSAGE, this.SUCCESS);
    this.router.navigate([this.HOME_URL]);
  }

  clickHamburgerBtn() {
    this.hamburgerBtnClass =
      (this.hamburgerBtnClass === this.BTN_CLOSE)
        ? this.BTN_OPEN
        : this.BTN_CLOSE;

    this.hamburgerDivClass =
      (this.hamburgerDivClass === this.DIV_CLOSE)
        ? this.DIV_OPEN
        : this.DIV_CLOSE;
  }
}
