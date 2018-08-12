import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {adminComponents} from './index';
import {adminRoutes} from './admin.routing';

@NgModule({
  imports: [
    FormsModule,
    RouterModule.forChild(adminRoutes)
  ],
  declarations: [
    ...adminComponents
  ]
})
export class AdminModule {
}
