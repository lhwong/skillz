import {Component, HostBinding} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {KeycloakAuthGuard, KeycloakService} from 'keycloak-angular';

import { User } from './user';
import { UsersService } from './users.service';

@Component({
  selector: 'app-login',
  providers: [UsersService],
  styleUrls: [ './home.style.scss' ],
  templateUrl: './home.template.html'
})
export class HomeComponent {
  @HostBinding('class') classes = 'login-page app';

  title = 'Demo';
  authenticated = false;
  greeting = {};

  users: User[];
  editUser: User; // the hero currently being edited

  constructor(private http: HttpClient, public keycloak: KeycloakService, private usersService: UsersService) {
    this.keycloak.isLoggedIn().then(() => {

      if (this.keycloak.getKeycloakInstance().authenticated) {
        console.log('authenticated');
        this.http.get('resource').subscribe(
          data => this.greeting = data, 
          () => { document.location.href = '/login' });
        
      }

    });
  }

  public isAuthenticated() {
    return this.keycloak.getKeycloakInstance().authenticated;
  }

  add(name: string): void {
    this.editUser = undefined;
    name = name.trim();
    if (!name) {
      return;
    }

    // The server will generate the id for this new hero
    const newUser: User = { name } as User;
    this.usersService
      .addUser(newUser)
      .subscribe(user => this.users.push(user));
  }
  
}
