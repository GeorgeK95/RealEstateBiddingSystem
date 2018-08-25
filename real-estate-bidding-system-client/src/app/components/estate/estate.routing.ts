import {NewEstateComponent} from './new-estate/new-estate.component';
import {EstateDetailsComponent} from './estate-details/estate-details.component';
import {AuthGuard} from '../../core/guard/auth/auth.guard';

const NEW_ESTATE = 'new';
const ESTATE_DETAILS = ':id';

export const estatesRoutes = [
  {path: NEW_ESTATE, canActivate: [AuthGuard], component: NewEstateComponent},
  {path: ESTATE_DETAILS, component: EstateDetailsComponent}
];
