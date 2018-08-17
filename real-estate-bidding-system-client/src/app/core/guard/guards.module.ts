import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EstateGuard} from './estates/estate.guard';
import {AdminGuard} from './admin/admin.guard';

@NgModule({
  providers: [EstateGuard, AdminGuard],
  imports: [
    CommonModule
  ]
})
export class GuardsModule {
}
