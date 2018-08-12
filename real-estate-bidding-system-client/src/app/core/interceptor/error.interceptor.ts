import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptor implements HttpInterceptor {
  readonly UNAUTHORIZED_STATUS_CODE = 401;
  readonly BAD_REQUEST_STATUS_CODE = 400;
  readonly CONFLICT_STATUS_CODE = 409;
  readonly ERROR = 'Error!';
  readonly WARNING = 'Warning!';
  readonly INVALID_DATA_PROVIDED = 'Invalid data provided !';

  constructor(private toastr: ToastrService) {
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
            }
          }

          return throwError(err.error);
        })
      );
  }
}
