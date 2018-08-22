import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRouting} from './app.routing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {ErrorInterceptor} from './core/interceptor/error.interceptor';
import {TokenInterceptor} from './core/interceptor/token.interceptor';
import {LoginInterceptor} from './core/interceptor/login.interceptor';
import {ProfileInterceptor} from './core/interceptor/profile.interceptor';
import {EstateInterceptor} from './core/interceptor/estate.interceptor';
import {NotFoundComponent} from './components/shared/not-found/not-found.component';
import {NavigationComponent} from './components/shared/navigation/navigation.component';
import {HttpClientService} from './core/service/http-client.service';
import { EstateDetailsComponent } from './components/estate/estate-details/estate-details.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AppRouting
    /* ,
     UserModule,
     EstateModule,
     ServiceModule,
     AdminModule*/
  ],
  providers: [
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
      useClass: ErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
