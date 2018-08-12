import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EstateGuard} from './estates/estate.guard';

@NgModule({
  providers: [EstateGuard],
  imports: [
    CommonModule
  ]
})
export class GuardsModule {
}
