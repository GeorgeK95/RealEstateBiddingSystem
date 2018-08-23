import {ProfileInterceptor} from './core/interceptor/profile.interceptor';
import {HttpClientService} from './core/service/http-client.service';
import {BidInterceptor} from './core/interceptor/bid.interceptor';
import {ErrorInterceptor} from './core/interceptor/error.interceptor';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from './core/interceptor/token.interceptor';
import {LoginInterceptor} from './core/interceptor/login.interceptor';
import {EstateInterceptor} from './core/interceptor/estate.interceptor';

export const appProviders = [
  HttpClientService,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoginInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: ProfileInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: EstateInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: BidInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
  }
];
