import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable()
export class LogoutService {
 

 
  constructor(private http: HttpClient) {
    
  }
 
  
 
  public logout(id: string) {
    return this.http.put('logout/' + id, {});
  }
}