import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import 'rxjs/add/operator/finally';
import {KeycloakAuthGuard, KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
  // template: `<router-outlet></router-outlet>`
  
})
export class AppComponent {
  title = 'Demo';
  authenticated = false;
  //greeting = {};

  constructor(private http: HttpClient, public keycloak: KeycloakService) {
   
    /*this.auth.isLoggedIn().then(() => {

        if (this.auth.getKeycloakInstance().authenticated) {
          console.log('authenticated');
          this.http.get('resource').subscribe(data => this.greeting = data);
        }

      });*/
  }

  public isAuthenticated() {
    return this.keycloak.getKeycloakInstance().authenticated;
  }






  logout() {
      /*this.http.post('logout', {}).finally(() => {
          this.authenticated = false;
      }).subscribe();*/

    // this.http.post('logout', {}).subscribe(response => {
      // this.auth.logout();
    //  console.log(response);
    // });

    this.http.post('logout', {}).subscribe(response => {
        console.log('logging out from Keycloak');
        this.keycloak.logout();
        //this.keycloak.getKeycloakInstance().logout();
    }, () => { console.log('logout'); });
    
    //this.keycloak.logout();

    


    
  }

  register() {
    
    this.keycloak.register({redirectUri: window.location.protocol + '//' + window.location.hostname + (window.location.port == '80' ? '' : ':' + window.location.port) + '/login'});
  }

}
