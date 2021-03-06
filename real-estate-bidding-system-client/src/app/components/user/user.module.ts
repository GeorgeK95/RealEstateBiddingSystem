import {NgModule} from '@angular/core';
import {userRoutes} from './user.routing';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {userComponents} from './index';
import {CommonModule} from '@angular/common';
import {HttpClientService} from '../../core/service/http-client.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(userRoutes)
  ],
  declarations: [
    ...userComponents
  ]
})
export class UserModule {
}
