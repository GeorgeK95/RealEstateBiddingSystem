import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponseBase} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {
  readonly ZERO = 0;
  readonly ONE = 1;
  readonly UNAUTHORIZED_STATUS_CODE = 401;
  readonly FORBIDDEN_STATUS_CODE = 403;
  readonly BAD_REQUEST_STATUS_CODE = 400;
  readonly CONFLICT_STATUS_CODE = 409;
  readonly ERROR = 'Error!';
  readonly WARNING = 'Warning!';
  readonly INVALID_DATA_PROVIDED = 'Invalid data provided !';
  readonly UNAUTHORIZED_MESSAGE = 'You don\'t have permission to view page content.';
  readonly UNAUTHORIZED = 'Unauthorized!';
  readonly HOME_URL = '/';
  readonly LOGIN_URL = 'http://localhost:8080/users/login';
  readonly REGISTER_URL = 'http://localhost:8080/users/register';
  readonly EDIT_PROFILE_URL = 'http://localhost:8080/users/details/';
  readonly ESTATES_NEW_URL = 'estates/new';
  readonly PUT = 'PUT';
  readonly INVALID_CREDENTIALS = 'Invalid or mismatch password.';

  constructor(private toastr: ToastrService, private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        catchError((err: HttpErrorResponse) => {
          if (err.status === this.CONFLICT_STATUS_CODE) {
            this.toastr.error(err.error.body, this.ERROR);
          } else {
            if (err.status === this.BAD_REQUEST_STATUS_CODE) {
              if (err.error === this.INVALID_CREDENTIALS ||
                err.error.message && err.error.message.localeCompare('Invalid credentials.') === 0) {
                // login with invalid credentials
                this.toastr.error(this.INVALID_CREDENTIALS, this.ERROR);
              } else {
                // noinspection TsLint
                for (const errorKey in err.error) {
                  // edit user profile with invalid credentials
                  this.toastr.error(err.error[errorKey],
                    (errorKey.charAt(this.ZERO).toUpperCase() + errorKey.slice(this.ONE))
                  );
                }
              }

              // this.toastr.error(this.INVALID_DATA_PROVIDED, this.ERROR);
            } else {
              if (err.status === this.FORBIDDEN_STATUS_CODE) {
                this.toastr.error(this.UNAUTHORIZED_MESSAGE, this.UNAUTHORIZED);
                this.router.navigate([this.HOME_URL]);
              }
            }
          }
          if (err.status === this.BAD_REQUEST_STATUS_CODE &&
            !err.url.endsWith(this.REGISTER_URL) &&
            !err.url.endsWith(this.LOGIN_URL) &&
            !err.url.endsWith(this.ESTATES_NEW_URL)) {
            this.toastr.error(this.INVALID_DATA_PROVIDED, this.ERROR);
            this.router.navigate([this.HOME_URL]);
          }

          return throwError(err.error);
        })
      );
  }
}
