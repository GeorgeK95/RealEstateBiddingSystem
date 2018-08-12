import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserModule} from './components/user/user.module';
import {SharedModule} from './components/shared/shared.module';
import {AppRouting} from './app.routing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {UserService} from './core/service/user/user.service';
import {EstateModule} from './components/estate/estate.module';
import {ServiceModule} from './core/service/services.module';
import {TermsComponent} from './components/shared/terms/terms.component';

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
    ServiceModule
  ],
  providers: [
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
