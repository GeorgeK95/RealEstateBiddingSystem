import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRouting} from './app.routing';
import {ToastrModule} from 'ngx-toastr';

export const appImports = [
  BrowserModule,
  HttpClientModule,
  BrowserAnimationsModule,
  ToastrModule.forRoot(),
  AppRouting
];
