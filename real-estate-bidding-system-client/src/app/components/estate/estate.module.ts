import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {estatesComponents} from './index';
import {estatesRoutes} from './estate.routing';
import {allServices} from '../../core/service';

@NgModule({
  imports: [
    FormsModule,
    RouterModule.forChild(estatesRoutes)
  ],
  declarations: [
    ...estatesComponents
  ]
})
export class EstateModule {
}
