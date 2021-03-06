package pjetwitter;

import helper.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KeywordsClassifier {
	private final String positiveFilePath = "data/positive.txt";
	private final String negativeFilePath = "data/negative.txt";
	
    private List<String> positiveWords;
    private List<String> negativeWords;
    
    public KeywordsClassifier() throws Exception {
    	this.positiveWords = new ArrayList<String>();
    	this.negativeWords = new ArrayList<String>();
    	
    	this.loadWords(this.positiveFilePath);
    	this.loadWords(this.negativeFilePath);
    }
    
    private void loadWords(String filePath) throws Exception {
    	BufferedReader br = new BufferedReader(new FileReader(filePath));
    	
    	String line = br.readLine();
    	
    	while(line != null) {
    		String[] words = line.split(", ");
    		
    		for(String word : words) {
    			if(filePath.contains("positive")) {
    				this.positiveWords.add(word.trim());
    			}
    			else {
    				this.negativeWords.add(word.trim());
    			}
    		}
    		
    		line = br.readLine();
    	}
    }
    
    public int classify(String tweet) {
    	String[] words = tweet.split(" ");
    	int count = 0;
    	
    	for(String word : words) {
    		if(this.positiveWords.contains(word)) {
    			count++;
    		}
    		else if(this.negativeWords.contains(word)) {
    			count--;
    		}
    	}
    	
    	if(count < 0) {
    		return Constants.NEGATIVE_TWEET;
    	}
    	
    	if(count > 0) {
    		return Constants.POSITIVE_TWEET;
    	}
    	
    	return Constants.NEUTRAL_TWEET;
    }
}
