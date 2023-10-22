package com.skillzstreet.talentspy.tenant.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.skillzstreet.talentspy.master.entity.Tenant;
import com.skillzstreet.talentspy.master.repository.TenantRepository;
import com.skillzstreet.talentspy.util.TenantContextHolder;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = LoggerFactory
            .getLogger(TenantInterceptor.class);
    
	@Autowired
    private TokenStore tokenStore;
	
	@Autowired
	private TenantRepository tenantRepository;

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

    	String subDomainName = request.getRemoteHost().substring(0, request.getRemoteHost().indexOf("."));
    	
    	LOG.debug("Sub domain name: " + subDomainName);
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
	     
    	//System.out.println(oauthDetails.getTokenValue());
    	
    	OAuth2AccessToken accessToken = tokenStore
	        .readAccessToken(oauthDetails.getTokenValue());
        Map<String, Object> details = (Map<String, Object>) accessToken.getAdditionalInformation();
    	
        System.out.println(details.get("org"));
        
        
        Tenant tenant = tenantRepository.findByName(subDomainName);
        
        LOG.debug("Tenant: " + tenant.getName() + " " + tenant.getId());
        
        //TenantContextHolder.setTenantId("8b1b1052-dcc4-4a42-aeb5-1e4f7305b204");
        //TenantContextHolder.setTenantId("c7e07a33-b2a2-4108-9b1b-55e479021955");
        TenantContextHolder.setTenantId("790e3041-6bf6-4ef6-a644-187cc2907543");
        //TenantContextHolder.setTenantId(tenant.getId());
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContextHolder.clear();
    }
    
    

}
