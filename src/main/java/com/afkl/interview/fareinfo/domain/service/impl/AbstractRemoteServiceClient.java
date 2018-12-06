package com.afkl.interview.fareinfo.domain.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @author tbansal
 *
 */
public class AbstractRemoteServiceClient {

	@Value("${remote.travel.api.base.url}")
	public String remoteBaseUrl;

	protected RestTemplate restTemplate = new RestTemplate();

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractRemoteServiceClient.class);

}