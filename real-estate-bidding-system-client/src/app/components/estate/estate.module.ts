import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {estatesComponents} from './index';
import {estatesRoutes} from './estate.routing';
import {CommonModule} from '@angular/common';
import {CustomFormsModule} from 'ng2-validation';
import {HttpClientService} from '../../core/service/http-client.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CustomFormsModule,
    RouterModule.forChild(estatesRoutes)
  ],
  declarations: [
    ...estatesComponents
  ]
})
export class EstateModule {
}
