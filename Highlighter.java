import java.util.*;
import java.io.*;
import java.lang.StringBuilder;
import java.util.regex.*;

public class Highlighter {
 	private Queue<HtmlTag> tagQueue;    
    private StringBuilder htmlText = new StringBuilder();
    public String resultStr;
    //public StringBuilder validatedTag = new StringBuilder();
    // Constructors
    public Highlighter(String str, Queue<HtmlTag> tagQueue) throws IllegalArgumentException{
    	htmlText = new StringBuilder(str);
    	this.tagQueue=tagQueue;
    }

    public String highlight() {
    	TagStack myStack = new TagStack();
    	while(!tagQueue.isEmpty()){
    		HtmlTag currentTag=tagQueue.poll();
			String currentTagPattern = "<[ ]*"+currentTag.getElement()+"[^>]*>";
			Pattern currentTagPatternPattern = Pattern.compile(currentTagPattern);
			Matcher currentTagMatcher = currentTagPatternPattern.matcher(htmlText);
    		if(currentTag.isOpenTag()){
    			if(!currentTag.isSelfClosing())
    				myStack.push(currentTag);

				if(currentTagMatcher.find()){
					int currentTagStartIndex = currentTagMatcher.start();
					htmlText.insert(currentTagStartIndex, currentTag.colorMatch());//currentTagStartIndex如果是0怎么解决？？
					int currentTagEndIndex = currentTagMatcher.end();
					resultStr+=htmlText.substring(0,currentTagEndIndex);
					htmlText=new StringBuilder(htmlText.substring(currentTagEndIndex));
					HtmlTag nextTag = tagQueue.poll();
	    			String nextTagPattern = "<[ ]*[/]?[ ]*"+nextTag.getElement()+"[^>]>";
					Pattern nextTagPatternPattern = Pattern.compile(nextTagPattern);
					Matcher nextTagMatcher = nextTagPatternPattern.matcher(htmlText);//有错误
					if(nextTagMatcher.find()){
						int nextTagStartIndex = nextTagMatcher.start();//有错误
						resultStr+=htmlText.substring(0,nextTagStartIndex);
						htmlText=new StringBuilder(htmlText.substring(nextTagStartIndex+1));
					}
				}
			}else{
				myStack.pop();//if the currentTag is closing, pop() its correspoding openTag from Stack		
				if(!tagQueue.isEmpty()){
					HtmlTag nextTag = tagQueue.peek();
					if(!nextTag.isOpenTag()){//if the next tag is closing tag or no more tag
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							htmlText.insert(currentTagEndIndex, myStack.peek().colorMatch());
							resultStr+=htmlText.substring(0,currentTagEndIndex-1);
							htmlText=new StringBuilder(htmlText.substring(currentTagEndIndex));
						}
					}else{
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							resultStr+=htmlText.substring(0,currentTagEndIndex-1);
							htmlText=new StringBuilder(htmlText.substring(currentTagEndIndex));
						}
					}
				}
			}
		}
		return resultStr;
	}
}    


