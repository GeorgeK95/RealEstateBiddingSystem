import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {estatesComponents} from './index';
import {estatesRoutes} from './estate.routing';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(estatesRoutes)
  ],
  declarations: [
    ...estatesComponents
  ]
})
export class EstateModule {
}
