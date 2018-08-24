import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {UserProfileComponent} from './profile/user-profile.component';
import {AuthGuard} from '../../core/guard/auth/auth.guard';
import {HomeComponent} from '../shared/home/home.component';
import {UserEstatesComponent} from './user-estates/user-estates.component';
import {UserBidsComponent} from './user-bids/user-bids.component';

const REGISTER = 'register';
const LOGIN = 'login';
const DETAILS_ID = ':id';
const DETAILS_ID_ESTATES = ':id/estates';
const DETAILS_ID_BIDS = ':id/bids';

export const userRoutes = [
  {path: REGISTER, component: RegisterComponent},
  {path: LOGIN, component: LoginComponent},
  {path: DETAILS_ID, canActivate: [AuthGuard], component: UserProfileComponent},
  {path: DETAILS_ID_ESTATES, canActivate: [AuthGuard], component: UserEstatesComponent},
  {path: DETAILS_ID_BIDS, canActivate: [AuthGuard], component: UserBidsComponent}
];
