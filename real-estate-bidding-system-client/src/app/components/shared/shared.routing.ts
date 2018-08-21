import {TermsComponent} from './terms/terms.component';
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {ContactsComponent} from './contacts/contacts.component';

const HOME = '';
const TERMS = 'terms';
const FULL = 'full';
const ABOUT = 'about';
const CONTACTS = 'contacts';

export const sharedRoutes = [
  {path: HOME, component: HomeComponent, pathMatch: FULL},
  {path: TERMS, component: TermsComponent},
  {path: ABOUT, component: AboutComponent},
  {path: CONTACTS, component: ContactsComponent}
];
