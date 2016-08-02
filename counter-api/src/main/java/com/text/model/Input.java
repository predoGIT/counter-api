package com.text.model;

import java.io.Serializable;
import java.util.List;

public class Input implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> searchText;

	public List<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
	
	public String toString() {
		return "searchText: " + searchText;
	}
	
}
