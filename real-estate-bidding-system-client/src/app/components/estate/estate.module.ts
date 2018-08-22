import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {estatesComponents} from './index';
import {estatesRoutes} from './estate.routing';
import {CommonModule} from '@angular/common';
import {CustomFormsModule} from 'ng2-validation';
import {FileUploadModule} from 'ng2-file-upload';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CustomFormsModule,
    FileUploadModule,
    RouterModule.forChild(estatesRoutes)
  ],
  declarations: [
    ...estatesComponents
  ]
})
export class EstateModule {
}
