import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {userRoutes} from './user.routing';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {userComponents} from './index';

@NgModule({
  imports: [
    FormsModule,
    RouterModule.forChild(userRoutes)
  ],
  declarations: [
    ...userComponents
  ]
})
export class UserModule {
}
