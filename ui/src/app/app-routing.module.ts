import { NgModule }             from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';

import { ErrorComponent } from './error/error.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'logout', component: LogoutComponent },
  { path: 'home', component: HomeComponent },
  { path: 'error', component: ErrorComponent }, 
  { path: '**', component: ErrorComponent }
];


@NgModule({
  imports: [ RouterModule.forRoot(routes, {
    useHash: true,
    preloadingStrategy: PreloadAllModules,
    enableTracing: true
  }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
