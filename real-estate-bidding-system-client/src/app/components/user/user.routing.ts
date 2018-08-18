import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';
import {UserProfileComponent} from './profile/user-profile.component';
import {AuthGuard} from '../../core/guard/auth/auth.guard';

const REGISTER = 'register';
const LOGIN = 'login';
const DETAILS_ID = 'details/:id';

export const userRoutes = [
  {path: REGISTER, component: RegisterComponent},
  {path: LOGIN, component: LoginComponent},
  {path: DETAILS_ID, canActivate: [AuthGuard], component: UserProfileComponent}
];
