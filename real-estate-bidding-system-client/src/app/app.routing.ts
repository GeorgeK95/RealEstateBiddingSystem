import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {NotFoundComponent} from './components/shared/not-found/not-found.component';
import {UserModule} from './components/user/user.module';
import {EstateModule} from './components/estate/estate.module';
import {AdminModule} from './components/admin/admin.module';
import {AdminGuard} from './core/guard/admin/admin.guard';
import {SharedModule} from './components/shared/shared.module';
import {AuthGuard} from './core/guard/auth/auth.guard';

const USERS = 'users';
const ESTATES = 'estates';
const ADMIN = 'admin';
const ALL = '**';
const HOME = '';

const appRoutes = [
  {path: HOME, loadChildren: () => SharedModule},
  {path: USERS, loadChildren: () => UserModule},
  {path: ESTATES,  canActivate: [AuthGuard], loadChildren: () => EstateModule},
  {path: ADMIN, canActivate: [AdminGuard], loadChildren: () => AdminModule},
  {path: ALL, component: NotFoundComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRouting {
}
