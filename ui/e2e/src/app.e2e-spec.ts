import { AppPage } from './app.po';
import { LoginPage } from './login.po';
import { RegisterPage } from './register.po';
import { browser, by, element, until } from 'protractor';
import { By } from 'selenium-webdriver';

describe('given that I have not signed in', () => {
  let page: AppPage;
  

  beforeEach(() => {
    page = new AppPage();
  });

  it('when accessing landing page I should see welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Greeting');
    
  });

  

});

describe('Access protected URL', () => {
  let page: AppPage;
  let loginPage: LoginPage;
  let registerPage: RegisterPage;



  describe('Given that I have not signed in', () => {
    
    beforeEach(() => {
      page = new AppPage();
      loginPage = new LoginPage();
      registerPage = new RegisterPage();
    });

    
    it('when clicking on login link, then the browser should be redirected to login page', () => {
      page.navigateTo();
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Login')), 10000);
        expect(element(by.linkText('Login')).isDisplayed()).toBeTruthy();
        element(by.linkText('Login')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/openid-connect/auth'), 10000);
      
    });

    it('when clicking on Explore all courses link, then the browser should be redirected to login page', () => {
      page.navigateTo();
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
        expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
        element(by.linkText('Explore all courses')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/saml'), 10000);
      
    });

  });

});

describe('Single sign on', () => {
  let page: AppPage;
  let loginPage: LoginPage;
  let registerPage: RegisterPage;

  
  
  describe('Scenario: Sign in via Skillzstreet - Given that I have not signed in when clicking on Login link and signing in', () => {
    beforeAll(() => {
      page = new AppPage();
      loginPage = new LoginPage();
      registerPage = new RegisterPage();
      loginPage.login('newuser19@thecads.com');
    });

    it('then should display logout link', () => {
      
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
        expect(element(by.linkText('Logout')).isDisplayed()).toBeTruthy();
        expect(element(by.css('a[href="/login"]')).isDisplayed()).toBeFalsy();
      });
    });

    it('and should be able to access resource API endpoint - token is relayed', () => {
      expect(element(by.cssContainingText('p', 'Hello World')).isDisplayed()).toBeTruthy();
    });

    it('and access LMS dashboard', () => {
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
        expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
        element(by.linkText('Explore all courses')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/dashboard?'), 10000);
      
    });

    afterAll(() => {
      browser.restart();
      /*page.navigateTo();

      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
        element(by.linkText('Logout')).click();
      });*/
    });

  });

  

  describe('Scenario: Sign in via LMS - Given that I have not signed in when clicking on Explore all courses link and signing in', () => {
    beforeAll(() => {
      page = new AppPage();
      loginPage = new LoginPage();
      registerPage = new RegisterPage();
      page.navigateTo();
      //loginPage.login('newuser18@thecads.com');

      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
        expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
        element(by.linkText('Explore all courses')).click();
        
        browser.waitForAngularEnabled(false);
        element(by.id('username')).sendKeys('newuser19@thecads.com');
        element(by.id('password')).sendKeys('password');
        element(by.id('kc-login')).click();
      });
      
      //element(by.id('username')).sendKeys('newuser19@thecads.com');
      //element(by.id('password')).sendKeys('password');
      //element(by.id('kc-login')).click();

      
      
    });

    it('then the browser should be redirected to LMS dashboard', () => {
      browser.driver.wait(until.urlContains('/dashboard?'), 10000);
      
    });


    it('and should display logout link on landing page', () => {
      browser.get('/');
      
      browser.waitForAngular().then(function() {
        //work around for execution on github action
        browser.driver.wait(until.urlContains('/#/home'), 20000);
        browser.driver.wait(until.elementLocated(by.linkText('Logout')), 20000);
        expect(element(by.linkText('Logout')).isDisplayed()).toBeTruthy();
        expect(element(by.css('a[href="/login"]')).isDisplayed()).toBeFalsy();
      });
    });

    it('and should be able to access resource API endpoint - token is relayed', () => {
      

      browser.waitForAngular().then(function() {
        expect(element(by.cssContainingText('p', 'Hello World')).isDisplayed()).toBeTruthy();
      });

    });

 1   

    afterAll(() => {
      browser.restart();
      /*browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
        element(by.linkText('Logout')).click();
        browser.driver.wait(until.elementLocated(by.linkText('Login')), 10000);
      });*/
    });

    
  });

  

  

});


describe('Single Log Out', () => {
  let page: AppPage;
  let loginPage: LoginPage;
  let registerPage: RegisterPage;

  
  describe('Scenario: Sign in and out from Skillzstreet', () => {
  
    describe('Given that I signed in to Skillzstreet', () => {
      
      beforeAll(() => {
        page = new AppPage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        loginPage.login('newuser19@thecads.com');
      });

      it('and signed out', () => {

        browser.waitForAngular().then(function() {
          browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
          element(by.linkText('Logout')).click();
        });

      });

      
        
      it('When clicking on login link, then the browser should be redirected to login page', () => {
        browser.get('/');
        browser.waitForAngular().then(function() {
          browser.driver.wait(until.urlContains('/#/home'), 20000);
          browser.driver.wait(until.elementLocated(by.linkText('Login')), 10000);
          expect(element(by.linkText('Login')).isDisplayed()).toBeTruthy();
          element(by.linkText('Login')).click();
        });  
        
        browser.waitForAngularEnabled(false);
        browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/openid-connect/auth'), 10000);
        
      });
      

      
      it('when clicking on Explore all courses link, then the browser should be redirected to login page', () => {
        browser.get('/');
        browser.waitForAngular().then(function() {
          browser.driver.wait(until.urlContains('/#/home'), 20000);
          browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
          expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
          element(by.linkText('Explore all courses')).click();
        });  
        
        browser.waitForAngularEnabled(false);
        browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/saml'), 10000);
        
      });


      afterAll(() => {
        browser.restart();
        
      });

      
    });  
    

  });    

  describe('Scenario: Logout from Skillzstreet and LMS - Given that I signed in', () => {
    beforeAll(() => {
      page = new AppPage();
      loginPage = new LoginPage();
      registerPage = new RegisterPage();
      page.navigateTo();
      loginPage.login('newuser19@thecads.com');
    });

    it('and access LMS dashboard - auto signing in to LMS', () => {
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
        expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
        element(by.linkText('Explore all courses')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/dashboard?'), 10000);
      
      
    });

    it('when signing out', () => {
      
      browser.get('/');
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.urlContains('/#/home'), 20000);
        browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
        element(by.linkText('Logout')).click();
      });

    });

    it('then the browser should be redirected to login page when clicking on Login', () => {
      //wait until logging out from LMS and Keycloak 
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.urlContains('/#/home'), 20000);
        browser.driver.wait(until.elementLocated(by.linkText('Login')), 20000);
        expect(element(by.linkText('Login')).isDisplayed()).toBeTruthy();
        element(by.linkText('Login')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/openid-connect/auth'), 10000);
      
    });
    

    
    it('and the browser should be redirected to login page when clicking on Explore all courses', () => {
      page.navigateTo();
      browser.waitForAngular().then(function() {
        browser.driver.wait(until.elementLocated(by.linkText('Explore all courses')), 10000);
        expect(element(by.linkText('Explore all courses')).isDisplayed()).toBeTruthy();
        element(by.linkText('Explore all courses')).click();
      });  
      
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.urlContains('/auth/realms/demo/protocol/saml'), 10000);
      
    });
    

    
  });

  

  afterAll(() => {
    //loginPage.logout();
    browser.restart();
  });

});





describe('when signing up', () => {
  let page: AppPage;
  let loginPage: LoginPage;
  let registerPage: RegisterPage;

  beforeAll(() => {
    page = new AppPage();
    loginPage = new LoginPage();
    registerPage = new RegisterPage();
    page.navigateTo();
    registerPage.register();
  });

  it('then I should be able to access resource API endpoint', () => {
    
    browser.waitForAngular().then(function() {
      browser.driver.wait(until.elementLocated(By.xpath("//p[text()='The content is Hello World']")), 10000);
      expect(element(by.cssContainingText('p', 'Hello World')).isDisplayed()).toBeTruthy();

    });
  });

  it('and access LMS dashboard - account is created and is automatically “linked” to the external provider', () => {
    
    
    browser.waitForAngular().then(function() {
      browser.driver.wait(until.elementLocated(by.css('a[href="http://ec2-18-140-93-224.ap-southeast-1.compute.amazonaws.com/dashboard?tpa_hint=saml-default"]')), 10000);
      expect(element(by.css('a[href="http://ec2-18-140-93-224.ap-southeast-1.compute.amazonaws.com/dashboard?tpa_hint=saml-default"]')).isDisplayed()).toBeTruthy();
      element(by.css('a[href="http://ec2-18-140-93-224.ap-southeast-1.compute.amazonaws.com/dashboard?tpa_hint=saml-default"]')).click();
      browser.waitForAngularEnabled(false);
      browser.driver.wait(until.elementLocated(by.linkText('Explore Courses')), 50000);

    });
  });

  
  afterAll(() => {
    //loginPage.logout();
    page.navigateTo();
    browser.waitForAngular().then(function() {
      browser.driver.wait(until.elementLocated(by.linkText('Logout')), 10000);
      element(by.linkText('Logout')).click();
      browser.driver.wait(until.elementLocated(by.linkText('Login')), 10000);
    });
  });

  

});




