package com.text.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.text.model.Paragraph;
import com.text.model.Word;

public class Utility {

	private final static Logger log = Logger.getLogger(Utility.class);
	
	/**
	 * Retrieves the text content of a document.
	 * @param String fileName
	 * @param String charSet
	 * @return String
	 */
	public static String retrieveContent(String fileName, String charSet) {
		log.info("FileName=" + fileName + "; CharSet=" + charSet +";");
		StringBuilder sb = new StringBuilder("");
		try (InputStream is = Paragraph.class.getResourceAsStream(fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(is, charSet)))  {
			
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + " ");
			}

		} catch(Exception e) {
			log.error("Exception in retrieveContent :: " + e.getMessage());
		} finally {
			log.info("File Content :: " + sb);	
		}		
		return sb.toString();
	}
	
	/**
	 * Returns the list of words with their number of occurrences in the paragraph. 
	 * @param String content
	 * @return List<Word>
	 */
	public static List<Word> getWordList(String content) {
		List<Word> wordList = new ArrayList<>();
		try {
			String contentLowerCase = content.toLowerCase();
			String[] arrText =  contentLowerCase.split("[ .,]");
			Set<String> textSet = new HashSet<>(Arrays.asList(arrText));
			
			for(String text : textSet) {
				wordList.add(new Word(text, StringUtils.countOccurrencesOf(contentLowerCase, text)));
			}
			Collections.sort(wordList);
			
		} catch(Exception e) {
			log.error("Exception in getWordList :: " + e.getMessage());
		}	
		return wordList;
	} 
	
}
