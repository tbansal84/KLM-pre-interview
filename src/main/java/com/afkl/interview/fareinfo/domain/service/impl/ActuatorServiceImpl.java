package com.afkl.interview.fareinfo.domain.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.afkl.interview.fareinfo.domain.service.ActuatorService;
import com.afkl.interview.fareinfo.dtos.ApiStatistics;
import com.afkl.interview.fareinfo.dtos.RequestMatrics;
import com.afkl.interview.fareinfo.dtos.mapper.ApiStatisticsMapper;
import com.afkl.interview.fareinfo.exceptions.TravelApiException;

@Service
public class ActuatorServiceImpl extends AbstractRemoteServiceClient implements ActuatorService {

	@Value("${app.actuator.protocol}")
	private String mgmtAppProtocol;
	@Value("${management.address}")
	private String mgmtServerName;
	@Value("${management.server.port}")
	private String mgmtPort;

	@Value("${app.actuator.metric.httprequest.context}")
	private String httpreqMetricContext;

	@Override
	public ApiStatistics getStatistics() {
		LOGGER.debug("Method getStatistics {}", "getTravelFare");

		ResponseEntity<RequestMatrics> fareResponse = null;
		ApiStatistics details = null;
		try {
			fareResponse = restTemplate.getForEntity(
					mgmtAppProtocol + "://" + mgmtServerName + ":" + mgmtPort + "/" + httpreqMetricContext,
					RequestMatrics.class);
			details = ApiStatisticsMapper.convertToAPIStatistics(fareResponse.getBody());
		} catch (Exception e) {
			LOGGER.error("Exception while calling remote service ", e);
			throw new TravelApiException(e);
		}

		return details;
	}

}