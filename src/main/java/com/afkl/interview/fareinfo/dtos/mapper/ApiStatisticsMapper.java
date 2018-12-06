package com.afkl.interview.fareinfo.dtos.mapper;

import com.afkl.interview.fareinfo.dtos.ApiStatistics;
import com.afkl.interview.fareinfo.dtos.RequestMatrics;

public class ApiStatisticsMapper {
	
	//use custom MeterRegistry for other stats- it is a lengthy task
	public static ApiStatistics convertToAPIStatistics(RequestMatrics metrics) {
		ApiStatistics stats = new ApiStatistics();
		if (metrics.getMeasurements() != null) {
			metrics.getMeasurements().stream().filter(m -> m.getStatistic().equals("COUNT")).findFirst()
					.ifPresent(c -> stats.setTotalRequests(c.getValue()));
			metrics.getMeasurements().stream().filter(m -> m.getStatistic().equals("MAX")).findFirst()
					.ifPresent(c -> stats.setMaxResponseTime(c.getValue()));
			metrics.getMeasurements().stream().filter(m -> m.getStatistic().equals("TOTAL_TIME")).findFirst()
					.ifPresent(c -> stats.setAvgResponseTime(c.getValue() / stats.getTotalRequests()));
		}
		return stats;
	}
}
