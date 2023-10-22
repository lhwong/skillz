import { browser, by, element, until } from 'protractor';

export class RegisterPage {
  id:string;

  constructor() {
    let d = new Date();
    this.id = d.getFullYear().toString() + d.getMonth().toString() + d.getDate().toString() + d.getHours().toString() + d.getMinutes().toString() + d.getSeconds().toString();

  }

  register() {
    browser.waitForAngularEnabled(false);
    element(by.linkText('Sign Up')).click();

    
    element(by.id('firstName')).sendKeys('Test');
    element(by.id('lastName')).sendKeys('Reg ' + this.id);
    element(by.id('email')).sendKeys(this.id + '@thecads.com');
    element(by.id('password')).sendKeys('password');
    element(by.id('password-confirm')).sendKeys('password');
    element(by.css('input[value="Register"]')).click();
  }

  


}
