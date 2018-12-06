package com.afkl.interview.fareinfo.dtos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "measurements", "availableTags" })
public class RequestMatrics {

	@JsonProperty("name")
	private String name;
	@JsonProperty("measurements")
	private List<Measurement> measurements = null;
	@JsonProperty("availableTags")
	private List<AvailableTag> availableTags = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("measurements")
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	@JsonProperty("measurements")
	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	@JsonProperty("availableTags")
	public List<AvailableTag> getAvailableTags() {
		return availableTags;
	}

	@JsonProperty("availableTags")
	public void setAvailableTags(List<AvailableTag> availableTags) {
		this.availableTags = availableTags;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
