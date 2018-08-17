import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from '../../service/user/user.service';
import {UserResponseModel} from '../../model/response/user-response.model';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
  readonly LOGIN_URL = '/users/login';
  readonly UNAUTHORIZED_MESSAGE = 'You don\'t have permission to view page content.';
  readonly UNAUTHORIZED = 'Unauthorized!';

  constructor(private service: UserService, private router: Router, private toastr: ToastrService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    /*this.service.getCurrentlyLoggedInUser()
      .subscribe((res: UserResponseModel) => {
        const isAdmin = res.rolesList.length > 1;

        if (isAdmin) {
          return true;
        }
      });*/

    if (this.service.checkIfAdmin()) {
      return true;
    }

    this.router.navigate([this.LOGIN_URL]);
    this.toastr.error(this.UNAUTHORIZED_MESSAGE, this.UNAUTHORIZED);

    return false;
  }
}
