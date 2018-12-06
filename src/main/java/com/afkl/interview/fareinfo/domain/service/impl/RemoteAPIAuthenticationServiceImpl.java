package com.afkl.interview.fareinfo.domain.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Service;

import com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;

/**
 * @author Tbansal
 *
 */
@Service
public class RemoteAPIAuthenticationServiceImpl extends AbstractRemoteServiceClient
		implements RemoteAPIAuthenticationService {
	
	@Value(value = "${remote.travel.api.auth.client.id}")
	public String authClentId;

	@Value(value = "${remote.travel.api.auth.client.secret}")
	public String authClentSecret;

	@Value(value = "${remote.travel.api.auth.service.endpoint}")
	public String authServiecEndpoint;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.afkl.interview.fareinfo.domain.service.RemoteAPIAuthenticationService#
	 * getToken()
	 */
	@Override
	public String getToken() {
		String token=null;
		try {
			token = oauth2RestTemplate().getAccessToken().getValue();
		} catch (UserRedirectRequiredException e) {
			this.LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}
		return token;

	}

	private OAuth2RestOperations oauth2RestTemplate() {
		AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri(remoteBaseUrl + authServiecEndpoint);
		resource.setClientId(authClentId);
		resource.setClientSecret(authClentSecret);
		resource.setClientAuthenticationScheme(AuthenticationScheme.header);
		return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(accessTokenRequest));
	}

}
