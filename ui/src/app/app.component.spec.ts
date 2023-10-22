import { TestBed, async, getTestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {KeycloakAuthGuard, KeycloakService} from 'keycloak-angular';

describe('AppComponent', () => {
  

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [KeycloakService],
      declarations: [
        AppComponent
      ]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Demo');
  }));
  xit('should render title in a h1 tag', async(() => {
    
    const service = TestBed.get(KeycloakService);
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;

    expect(compiled.querySelector('h1').textContent).toContain('Greeting');
  }));
  it('should fetch data from backend', async(() => {
    const http = TestBed.get(HttpTestingController);
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    http.expectOne('resource').flush({id: 'XYZ', content: 'Hello'});
    expect(app.data.content).toContain('Hello');
  }));
});
