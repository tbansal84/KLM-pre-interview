package com.afkl.interview.fareinfo.dtos;

public class ApiStatistics {

	private double totalRequests;
	private double totalOKRequests;
	private double total4XXRequests;
	private double total5XXRequests;
	private double avgResponseTime;
	private double minResponseTime;
	private double maxResponseTime;

	public double getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(double totalRequests) {
		this.totalRequests = totalRequests;
	}

	public double getTotalOKRequests() {
		return totalOKRequests;
	}

	public void setTotalOKRequests(double totalOKRequests) {
		this.totalOKRequests = totalOKRequests;
	}

	public double getTotal4XXRequests() {
		return total4XXRequests;
	}

	public void setTotal4XXRequests(double total4xxRequests) {
		total4XXRequests = total4xxRequests;
	}

	public double getTotal5XXRequests() {
		return total5XXRequests;
	}

	public void setTotal5XXRequests(double total5xxRequests) {
		total5XXRequests = total5xxRequests;
	}

	public double getAvgResponseTime() {
		return avgResponseTime;
	}

	public void setAvgResponseTime(double avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}

	public double getMinResponseTime() {
		return minResponseTime;
	}

	public void setMinResponseTime(double minResponseTime) {
		this.minResponseTime = minResponseTime;
	}

	public double getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

}
