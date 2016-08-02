package com.text.model;

import java.io.Serializable;

public class Word implements Serializable, Comparable<Word> {

	private static final long serialVersionUID = 1L;
	
	private String text;
	private Integer count;
	
	public Word(String text, Integer count) {
		this.text = text;
		this.count = count;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public int compareTo(Word word) {
		
		int sComp = word.count.compareTo(this.count); 
		
		if(sComp != 0) {
			return sComp;
		} else {
			return this.text.compareTo(word.text);	
		}
		
	}
	
}
