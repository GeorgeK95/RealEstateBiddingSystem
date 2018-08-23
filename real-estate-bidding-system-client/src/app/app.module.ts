import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {appProviders} from './app.providers';
import {appImports} from './app.imports';
import {appDeclarations} from './app.declarations';

@NgModule({
  declarations: appDeclarations,
  imports: appImports,
  providers: appProviders,
  bootstrap: [AppComponent]
})
export class AppModule {
}
