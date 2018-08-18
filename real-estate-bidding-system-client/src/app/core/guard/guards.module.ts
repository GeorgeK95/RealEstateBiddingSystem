import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AuthGuard} from './auth/auth.guard';
import {AdminGuard} from './admin/admin.guard';

@NgModule({
  providers: [AuthGuard, AdminGuard],
  imports: [
    CommonModule
  ]
})
export class GuardsModule {
}
