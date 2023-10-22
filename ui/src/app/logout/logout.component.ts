import {Component, HostBinding} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { JwtHelperService } from "@auth0/angular-jwt";
import { KeycloakService } from 'keycloak-angular';

import { LogoutService } from './logout.service';


@Component({
  selector: 'app-logout',
  providers: [LogoutService],
  templateUrl: './logout.template.html'
})
export class LogoutComponent {
  
  
  constructor(private http: HttpClient, public keycloak: KeycloakService, private router: Router, 
    private logoutService: LogoutService) {

    this.keycloak.isLoggedIn().then(() => {

      if (this.keycloak.getKeycloakInstance().authenticated) {

        this.keycloak.getToken().then(res => { 
          const helper = new JwtHelperService();

          const decodedToken = helper.decodeToken(res);
          
          this.logoutService.logout(decodedToken.sub).subscribe(() => {
            console.log('logged out');
            //this.router.navigate(['/home']);
            document.location.href = '/';
          });
        }); 
        
        
      }

    });


    

  }

  
  
}
