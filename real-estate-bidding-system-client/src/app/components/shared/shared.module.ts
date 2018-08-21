import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {sharedComponents} from './index';
import {sharedRoutes} from './shared.routing';

@NgModule({
  imports: [
    CommonModule,
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
