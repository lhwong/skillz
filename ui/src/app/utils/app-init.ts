import { KeycloakService } from 'keycloak-angular';

export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => keycloak.init({
    config: {
      url: 'http://18.139.4.3:8080/auth',
      realm: 'demo',
      clientId: 'js-client'
    },
    initOptions: {
      onLoad: 'check-sso',
      checkLoginIframe: false
    },
    enableBearerInterceptor: false,
    bearerExcludedUrls: ['/assets', '/clients/public']
  });
}
