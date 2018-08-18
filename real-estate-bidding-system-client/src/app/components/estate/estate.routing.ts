import {AllEstatesComponent} from './all-estates/all-estates.component';
import {NewEstateComponent} from './new-estate/new-estate.component';

const ALL_ESTATES = 'all';
const NEW_ESTATE = 'new';

export const estatesRoutes = [
  {path: ALL_ESTATES, component: AllEstatesComponent},
  {path: NEW_ESTATE, component: NewEstateComponent}
];
