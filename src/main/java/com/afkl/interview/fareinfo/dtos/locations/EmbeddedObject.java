package com.afkl.interview.fareinfo.dtos.locations;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "_embedded", "page" })
public class EmbeddedObject {

	@JsonProperty("_embedded")
	private Locations embedded;
	@JsonProperty("page")
	private Page page;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("_embedded")
	public Locations getEmbedded() {
		return embedded;
	}

	@JsonProperty("_embedded")
	public void setEmbedded(Locations embedded) {
		this.embedded = embedded;
	}

	@JsonProperty("page")
	public Page getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(Page page) {
		this.page = page;
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
