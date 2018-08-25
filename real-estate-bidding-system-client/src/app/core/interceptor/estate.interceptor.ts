import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class EstateInterceptor implements HttpInterceptor {
  readonly HOME_PAGE_URL = '/';
  readonly POST = 'POST';
  readonly PUT = 'PUT';
  readonly DELETE = 'DELETE';
  readonly RESPONSE_OK = 200;
  readonly RESPONSE_CREATED = 201;
  readonly RESPONSE_BAD_REQUEST = 400;
  readonly SUCCESS = 'Success.';
  readonly ERROR = 'Error.';
  readonly ESTATES_NEW_URL = 'estates/new';
  readonly HOME_URL = '';

  // readonly PROFILE_EDITED_SUCCESSFULLY_MESSAGE = 'Profile edited successfully.';

  constructor(
    private toastr: ToastrService,
    private router: Router
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        tap(
          (event: HttpEvent<any>) => {
            if (event instanceof HttpResponse && req.url.includes(this.ESTATES_NEW_URL)) {
              /*&& req.method === this.POST && event.status === this.RESPONSE_CREATED*/
              this.toastr.success(event.body, this.SUCCESS);
              this.router.navigate([this.HOME_PAGE_URL]);
            } else {
              if (event instanceof HttpResponse && req.method === this.PUT &&
                event.status === this.RESPONSE_OK && req.url.endsWith('/estates/new')) {
                this.toastr.success(event.body, this.SUCCESS);
                this.router.navigate([this.HOME_PAGE_URL]);
              } else {
                if (event instanceof HttpResponse && req.method === this.PUT &&
                  event.status === this.RESPONSE_BAD_REQUEST) {
                  this.toastr.error(event.body, this.ERROR);
                } else {
                  if (event instanceof HttpResponse && req.method === this.DELETE &&
                    event.status === this.RESPONSE_OK) {
                    this.toastr.success(event.body, this.SUCCESS);
                    this.router.navigate([this.HOME_URL]);
                  } else {
                    if (event instanceof HttpResponse && req.method === this.DELETE &&
                      event.status === this.RESPONSE_BAD_REQUEST) {
                      this.toastr.error(event.body, this.ERROR);
                    }
                  }
                }
              }
            }
          }
        )
      );
  }
}
