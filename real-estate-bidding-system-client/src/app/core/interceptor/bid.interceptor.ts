import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class BidInterceptor implements HttpInterceptor {
  readonly POST = 'POST';
  readonly PUT = 'PUT';
  readonly SUCCESS = 'Success.';
  readonly ERROR = 'Error.';
  readonly BIDS_URL = '/bids';

  readonly BID_MADE_SUCCESSFULLY_MESSAGE = 'Successfully placed a bid.';

  constructor(
    private toastr: ToastrService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        tap(
          (event: HttpEvent<any>) => {
            if (event instanceof HttpResponse && req.url.endsWith(this.BIDS_URL) && req.method === this.POST) {
              this.toastr.success(this.BID_MADE_SUCCESSFULLY_MESSAGE, this.SUCCESS);
            }
          }
        )
      );
  }
}
