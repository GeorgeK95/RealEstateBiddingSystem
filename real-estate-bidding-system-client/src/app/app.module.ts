import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserModule} from './components/user/user.module';
import {SharedModule} from './components/shared/shared.module';
import {AppRouting} from './app.routing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {UserService} from './core/service/user/user.service';
import {EstateModule} from './components/estate/estate.module';
import {ServiceModule} from './core/service/services.module';
import {ErrorInterceptor} from './core/interceptor/error.interceptor';
import {TokenInterceptor} from './core/interceptor/token.interceptor';
import {LoginInterceptor} from './core/interceptor/login.interceptor';
import {AdminModule} from './components/admin/admin.module';
import {ProfileEditInterceptor} from './core/interceptor/profile-edit.interceptor';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AppRouting,
    UserModule,
    SharedModule,
    EstateModule,
    ServiceModule,
    AdminModule
  ],
  providers: [
    UserService,
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
      useClass: ProfileEditInterceptor,
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
