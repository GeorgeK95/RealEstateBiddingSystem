import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../service/user/user.service';
import {LoginResponseModel} from '../model/response/login-response.model';

@Injectable({
  providedIn: 'root'
})
export class LoginInterceptor implements HttpInterceptor {
  readonly LOGIN = 'login';
  readonly REGISTER = 'register';
  // readonly USERNAME = 'username';
  readonly LOGIN_URL = '/users/login';
  readonly HOME_URL = '/';
  readonly AUTH_TOKEN = 'authtoken';
  readonly IS_ADMIN = 'is_admin';
  readonly SUCCESS = 'Success.';
  readonly TRUE = 'true';
  readonly FALSE = 'false';

  // readonly SIGNED_IN_SUCCESSFULLY_MESSAGE = 'Signed in successfully.';

  constructor(
    private toastr: ToastrService,
    private router: Router,
    private service: UserService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        tap(
          (event: HttpEvent<any>) => {
            if (event instanceof HttpResponse && req.url.endsWith(this.LOGIN)) {
              this.setDataToLocalStorage(event.body);
              this.toastr.success(event.body.message, this.SUCCESS);
              this.router.navigate([this.HOME_URL]);
            } else {
              if (event instanceof HttpResponse && req.url.endsWith(this.REGISTER)) {
                this.toastr.success(event.body.message, this.SUCCESS);
                this.router.navigate([this.LOGIN_URL]);
              }
            }
          }
        )
      );
  }

  private setDataToLocalStorage(res: LoginResponseModel): void {
    const isAdminEvaluation = res.admin === true ? this.TRUE : this.FALSE;
    localStorage.setItem(this.AUTH_TOKEN, res.accessToken);
    localStorage.setItem(this.IS_ADMIN, isAdminEvaluation);
    this.service.authToken = res.accessToken;
    this.service.isAdmin = isAdminEvaluation;
  }
}
