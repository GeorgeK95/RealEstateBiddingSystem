import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {sharedComponents} from './index';
import {sharedRoutes} from './shared.routing';
import {FormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(sharedRoutes)
  ],
  declarations: [
    ...sharedComponents
  ],
  exports: [
    ...sharedComponents
  ]
})

export class SharedModule {
}
