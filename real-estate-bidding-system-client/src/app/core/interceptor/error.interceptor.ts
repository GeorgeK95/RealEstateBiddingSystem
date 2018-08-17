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
              this.toastr.error(this.INVALID_DATA_PROVIDED, this.ERROR);
            } else {
              if (err.status === this.FORBIDDEN_STATUS_CODE) {
                this.toastr.error(this.UNAUTHORIZED_MESSAGE, this.UNAUTHORIZED);
                this.router.navigate([this.HOME_URL]);
              }
            }
          }

          return throwError(err.error);
        })
      );
  }
}
