import { browser, by, element, until } from 'protractor';

export class LoginPage {
  

  login(id:string) {
    browser.waitForAngularEnabled(false);
    browser.get('/login');

    element(by.id('username')).sendKeys(id);
    element(by.id('password')).sendKeys('password');
    element(by.id('kc-login')).click();
  }

  

  logout() {
    return element(by.linkText('Logout')).click();
  }


}
