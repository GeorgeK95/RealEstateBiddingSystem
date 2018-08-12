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
  readonly HOME_URL = '/';
  readonly SIGNED_OUT_SUCCESSFULLY_MESSAGE = 'Signed out successfully.';
  readonly SUCCESS = 'Success.';

  constructor(private service: UserService, private router: Router, private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  isLoggedIn() {
    return this.service.checkIfLoggedIn();
  }

  isAdmin() {
  }

  onLogout() {
    this.service.authToken = undefined;
    localStorage.clear();
    this.toastr.info(this.SIGNED_OUT_SUCCESSFULLY_MESSAGE, this.SUCCESS);
    this.router.navigate([this.HOME_URL]);
  }
}
