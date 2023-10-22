import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, PreloadAllModules } from '@angular/router';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppConfig } from './app.config';
import { ErrorComponent } from './error/error.component';
import { initializer } from './utils/app-init';
import { HomeComponent } from './home/home.component';
import { HttpErrorHandler }     from './http-error-handler.service';
import { MessageService }       from './message.service';
import { LogoutComponent } from './logout/logout.component';

const APP_PROVIDERS = [
  AppConfig
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ErrorComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    /*RouterModule.forRoot(ROUTES, {
      useHash: true,
      preloadingStrategy: PreloadAllModules,
      enableTracing: true
    }),*/
    AppRoutingModule,
    KeycloakAngularModule
  ],
  providers: [
    APP_PROVIDERS,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    },
    HttpErrorHandler,
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


