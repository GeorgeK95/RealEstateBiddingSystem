import {RegisterComponent} from './register/register.component';
import {LoginComponent} from './login/login.component';

const REGISTER = 'register';
const LOGIN = 'login';

export const userRoutes = [
  {path: REGISTER, component: RegisterComponent},
  {path: LOGIN, component: LoginComponent}
];
