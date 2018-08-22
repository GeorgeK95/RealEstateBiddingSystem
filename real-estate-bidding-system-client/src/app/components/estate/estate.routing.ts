import {AllEstatesComponent} from './all-estates/all-estates.component';
import {NewEstateComponent} from './new-estate/new-estate.component';
import {EstateDetailsComponent} from './estate-details/estate-details.component';
import {AuthGuard} from '../../core/guard/auth/auth.guard';

// const ALL_ESTATES = 'all';
const NEW_ESTATE = 'new';
const ESTATE_DETAILS = ':id';
// const FULL = 'full';

export const estatesRoutes = [
  // {path: ALL_ESTATES, component: AllEstatesComponent},
  {path: NEW_ESTATE, canActivate: [AuthGuard], component: NewEstateComponent},
  {path: ESTATE_DETAILS, component: EstateDetailsComponent}
];
