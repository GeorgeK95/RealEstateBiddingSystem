import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../core/service/user/user.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  readonly BTN_CLOSE = 'navbar-toggler collapsed';
  readonly BTN_OPEN = 'navbar-toggler';
  readonly DIV_CLOSE = 'navbar-collapse collapse';
  readonly DIV_OPEN = 'navbar-collapse collapse show';

  private hamburgerBtnClass: string;
  private hamburgerDivClass: string;

  constructor(private userService: UserService) {
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

  getCurrentUserId() {
    return this.parseJwt(this.userService.authToken).sub;
  }

  parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
  }

  onLogout() {
    this.userService.logout();
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
