package com.text.rs;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.text.model.Input;
import com.text.model.Output;
import com.text.model.Paragraph;
import com.text.model.Word;


@Controller
public class TextSearchController {

	private final static Logger log = Logger.getLogger(TextSearchController.class);

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Output searchText(@RequestBody Input input) {
		log.info("##### searchText start ######");
		log.info("Input :: " + input);
		Output result = new Output();
		try {
			Map<String, Integer> textCountMap = new LinkedHashMap<String, Integer>();
			String paragraphLowerCase = Paragraph.getContent().toLowerCase();
			
			for (String text : input.getSearchText()) {
				textCountMap.put(text, StringUtils.countOccurrencesOf(paragraphLowerCase, text.toLowerCase()));
			}
			result.setCounts(textCountMap);			
		} catch(Exception e) {
			log.error("Exception in searchText :: " + e.getMessage());
		} finally {
			log.info("Output :: " + result);
		}
		log.info("##### searchText end ######");
		return result;
	}
	
	@RequestMapping(value = "/top/{top}", method = RequestMethod.GET, produces = "text/csv")
	public @ResponseBody String top(@PathVariable Integer top, HttpServletResponse response) {
		log.info("##### top start ######");
		StringBuilder sb = new StringBuilder("");
		log.info("Input :: top=" + top);
		try {
			List<Word> wordList = Paragraph.getWordList();
			for(int i=0; i<top; i++) {
				sb.append(wordList.get(i).getText() + "|" + wordList.get(i).getCount() + "\n");
			}
			response.setHeader("Content-Disposition", "attachment; filename=top_" + top +".csv");
			
		} catch(Exception e) {
			log.error("Exception in top :: " + e.getMessage());
		} finally {
			log.info("Output :: " + sb.toString().replaceAll("\n", ","));
		}
		log.info("##### top end ######");
		return sb.toString();
	}
	
}
