package demo;

import javax.servlet.http.HttpServletResponse;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
@RestController
public class UiApplication extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(UiApplication.class);

	@Autowired
	TokenStore tokenStore;

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
	
	/*@GetMapping(value = "/{path:[^\\.]*}")
		public String redirect() {
	    return "forward:/";
	}*/
	
	/*@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		    // by default uses a Bean by the name of corsConfigurationSource
			//.cors()
			//.configurationSource(corsConfigurationSource())
			//.and()
		
			.logout()
			//.permitAll()
			//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
			    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			})
			.and()
			.authorizeRequests()
			    .requestMatchers(EndpointRequest.to("env")).permitAll()
			    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/index.html", "/", "/login", "/logout/**",
						"/**.js", "/**.js.map", "/**.woff", "/**.woff2", "/**.ttf", "/**.eot", "/**.svg", "/assets/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf()
			    .ignoringAntMatchers("/logout/**")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			// @formatter:on
	}
	
	@PutMapping(value = "/logout/{id}")
	public void logout(@PathVariable String id) {
		
		try {
			String serverUrl = "http://18.139.4.3:8080/auth";
			String realm = "demo";
			String clientId = "admin-api";
			String clientSecret = "b32080b3-d96c-4193-b76c-abfe56cf9848";
	
			// Client "idm-client" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
			Keycloak keycloak = KeycloakBuilder.builder() 
					.serverUrl(serverUrl)
					.realm(realm)
					.grantType(OAuth2Constants.CLIENT_CREDENTIALS) 
					.clientId(clientId) 
					.clientSecret(clientSecret)
					.build();
	
			// Get realm
			RealmResource realmResource = keycloak.realm(realm);
			realmResource.users().get(id).logout();
			keycloak.close();
			
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
		
		
	}
	
}

