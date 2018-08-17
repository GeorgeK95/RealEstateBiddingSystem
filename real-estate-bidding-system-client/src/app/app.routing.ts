import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {AboutComponent} from './components/shared/about/about.component';
import {ContactsComponent} from './components/shared/contacts/contacts.component';
import {NotFoundComponent} from './components/shared/not-found/not-found.component';
import {UserModule} from './components/user/user.module';
import {HomeComponent} from './components/shared/home/home.component';
import {EstateModule} from './components/estate/estate.module';
import {EstateGuard} from './core/guard/estates/estate.guard';
import {TermsComponent} from './components/shared/terms/terms.component';
import {AdminModule} from './components/admin/admin.module';
import {AdminGuard} from './core/guard/admin/admin.guard';

const FULL = 'full';
const ABOUT = 'about';
const USERS = 'users';
const ESTATES = 'estates';
const ADMIN = 'admin';
const CONTACTS = 'contacts';
const ALL = '**';
const HOME = '';
const TERMS = 'terms';

const appRoutes = [
  {path: HOME, component: HomeComponent, pathMatch: FULL},
  {path: TERMS, component: TermsComponent},
  {path: USERS, loadChildren: () => UserModule},
  {path: ABOUT, component: AboutComponent},
  {path: CONTACTS, component: ContactsComponent},
  {path: ESTATES, canActivate: [EstateGuard], loadChildren: () => EstateModule},
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
