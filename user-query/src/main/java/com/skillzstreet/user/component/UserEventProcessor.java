package com.skillzstreet.user.component;



import java.util.Arrays;
import java.util.Collections;
import javax.ws.rs.core.Response;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.skillzstreet.event.UserAddedEvent;

/*
* The @ProcessingGroup annotation hooks up this class up to an Axon Event Processor Group.
* The @EventHandler annotated method handles the Events of a given type.
*
* When the events come in, we persist a 'Product' entity to the JPA repository
* created for us by Spring Data Repositories.
*
* We have to organise the event handler into a Processor group as by default, processors read from the EventBus not AMQP,
* so we need to reconfigure things a bit - we need to add our event handler to a processor that reads from a Rabbit queue.
*
* So we annotate the class with @ProcessorGroup(“amqpEvents”) to configure the event processor (note default processor
* would use the package-name). We also need to add to the application.properties the following setting
* “axon.eventhandling.processors.amqpEvents.source=complaintEventsMethod”. You'll see this property in GitHub as that's
* where our configuration comes from for this app as it's been externalised by Spring Cloud Config.
*
* Note that the “complaintEventsMethod” keyword in the properties comes from (and must match) the @Bean name of the
* complaintEventsMethod(Serializer serializer) method in the AxonConfiguration class!
*/

@Component
@ProcessingGroup("user")
public class UserEventProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(UserEventProcessor.class);

    @Value("${lms.hostname}")
    private String lmsHostname;
    
    //private final CampaignRepository repo;
    
    
    
    public UserEventProcessor(/*CampaignRepository repository*/) {
        //this.repo = repository;
    }

    @EventHandler // Mark this method as an Axon Event Handler
    public void on(UserAddedEvent userAddedEvent) {
        //repo.save(new Campaign(campaignAddedEvent.getId(), campaignAddedEvent.getName()));
        LOG.info("A user was added! Id={} Name={}", userAddedEvent.getId(), userAddedEvent.getName());
        
        String serverUrl = "http://localhost:8080/auth";
		String realm = "demo";
		String clientId = "idm-client";
		String clientSecret = "a200cdf6-ad72-4f6c-af73-5b8e1cc48876";

		// Client "idm-client" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
		Keycloak keycloak = KeycloakBuilder.builder() 
				.serverUrl(serverUrl)
				.realm(realm)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS) 
				.clientId(clientId) 
				.clientSecret(clientSecret).build();

		
		// Define user
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername("tester1");
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmail("tom+tester1@tdlabs.local");
		user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

		// Get realm
		RealmResource realmResource = keycloak.realm(realm);
		UsersResource userResource = realmResource.users();
		
		// Create user (requires manage-users role)
		Response res = userResource.create(user);
		System.out.println("Repsonse: " + res.getStatusInfo());
		System.out.println(res.getLocation());
		String userId = res.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        
        /*RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
        map.add("name", userAddedEvent.getName());
        map.add("username", userAddedEvent.getId());
        map.add("password", "password");
        map.add("email", UUID.randomUUID().toString() + "@thecads.com");
        map.add("honor_code", Boolean.TRUE);
        map.add("term_of_service", Boolean.TRUE);
        map.add("verified", Boolean.TRUE);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

                
        ResponseEntity<String> response = restTemplate.exchange(lmsHostname + "/user_api/v1/account/registration/", HttpMethod.POST, request, String.class);
        
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            LOG.info(response.getBody());
        } else {
        	LOG.error(statusCode.getReasonPhrase());
        }*/
        
    }
}

