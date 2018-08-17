import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ProfileEditInterceptor implements HttpInterceptor {
  readonly PUT = 'PUT';
  readonly RESPONSE_OK = 200;
  readonly RESPONSE_BAD_REQUEST = 400;
  readonly SUCCESS = 'Success.';
  readonly ERROR = 'Error.';
  readonly PROFILE_EDITED_SUCCESSFULLY_MESSAGE = 'Profile edited successfully.';

  constructor(
    private toastr: ToastrService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        tap(
          (event: HttpEvent<any>) => {
            if (event instanceof HttpResponse && req.method === this.PUT &&
              event.status === this.RESPONSE_OK) {
              this.toastr.success(this.PROFILE_EDITED_SUCCESSFULLY_MESSAGE, this.SUCCESS);
            } else {
              if (event instanceof HttpResponse && req.method === this.PUT &&
                event.status === this.RESPONSE_BAD_REQUEST) {
                this.toastr.error(event.body, this.ERROR);
              }
            }
          }
        )
      );
  }
}
