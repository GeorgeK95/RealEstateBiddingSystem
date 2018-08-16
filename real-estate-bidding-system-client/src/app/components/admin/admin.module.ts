import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {adminComponents} from './index';
import {adminRoutes} from './admin.routing';
import {CommonModule} from '@angular/common';
import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    NgxPaginationModule,
    RouterModule.forChild(adminRoutes)
  ],
  declarations: [
    ...adminComponents
  ]
})
export class AdminModule {
}
