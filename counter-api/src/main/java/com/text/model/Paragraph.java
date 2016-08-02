package com.text.model;

import java.util.List;

import com.text.util.Utility;

public class Paragraph {
	
	private static String content;
	private static List<Word> wordList;
	
	// Initialise constant resources.
	static {
		content = Utility.retrieveContent("/sample.txt", "UTF-8");
		wordList = Utility.getWordList(content);
	}

	public static String getContent() {
		return content;
	}

	public static List<Word> getWordList() {
		return wordList;
	}
	
}
