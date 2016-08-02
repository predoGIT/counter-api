package com.text.model;

import java.io.Serializable;
import java.util.Map;

public class Output implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Integer> counts;

	public Map<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(Map<String, Integer> counts) {
		this.counts = counts;
	}
	
	public String toString() {
		return "counts: " + counts;
	}
	
}
