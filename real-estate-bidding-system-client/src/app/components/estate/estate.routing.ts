import {AllEstatesComponent} from './all-estates/all-estates.component';
import {NewEstateComponent} from './new-estate/new-estate.component';

const ALL_ESTATES = 'all';
const NEW_ESTATE = 'new';
const ESTATE_DETAILS = ':id';

export const estatesRoutes = [
  {path: ALL_ESTATES, component: AllEstatesComponent},
  {path: NEW_ESTATE, component: NewEstateComponent},
  {path: ESTATE_DETAILS, component: NewEstateComponent}
];
