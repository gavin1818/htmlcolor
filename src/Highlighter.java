import java.util.*;
import java.io.*;
import java.lang.StringBuilder;
import java.util.regex.*;

public class Highlighter {
 	private Queue<HtmlTag> tagQueue;    
    private StringBuilder htmlText = new StringBuilder();
    private String resultStr;
    //public StringBuilder validatedTag = new StringBuilder();
    // Constructors
    public Highlighter(String str, Queue<HtmlTag> tagQueue) throws IllegalArgumentException{
    	htmlText = new StringBuilder(str);
    	this.tagQueue=tagQueue;
    }

    public String highlight() {
    	/*myStack is only used to store openTag(not including self-closing tag)*/
    	TagStack myStack = new TagStack();
    	while(!tagQueue.isEmpty()){
    		//tagQueue contains all tags(open,close,selfclose) from tokenized html.
    		HtmlTag currentTag=tagQueue.poll();
			String currentTagPattern = "<[ ]*[/]?[ ]*"+currentTag.getElement()+"[^>]*>[\n\t ]*";
			Pattern currentTagPatternPattern = Pattern.compile(currentTagPattern);
			Matcher currentTagMatcher = currentTagPatternPattern.matcher(htmlText);
    		if(currentTag.isOpenTag()){
    			if(!currentTag.isSelfClosing()){
    				//it tag is not slefClose && not closing tag, push it on myStack. 
    				myStack.push(currentTag);
    			}
				if(currentTagMatcher.find()){
					//find index of the '<' in the current tag.
					int currentTagStartIndex = currentTagMatcher.start();
					htmlText.insert(currentTagStartIndex, currentTag.colorMatch());
					//find index of the '>' in the current tag.
					int currentTagEndIndex = currentTagMatcher.end(); //end() return the index of the matcher last+1; 
					resultStr+=htmlText.substring(0,currentTagEndIndex+currentTag.colorMatch().length());//substring(): The ending index is exclusive.
					htmlText.delete(0,currentTagEndIndex+currentTag.colorMatch().length());//delete():The ending index is exclusive.
					/*find the next tag's '>', and add the string before the '>' into resultStr, 
					and remove it from htmlText */
					//find the next tag's index, move all text sequnece before the tag into the resultStr.
					if(!tagQueue.isEmpty()){
						HtmlTag nextTag = tagQueue.peek();
	    				String nextTagPattern = "<[ ]*[/]?[ ]*"+nextTag.getElement()+"[^>]*>";
						Pattern nextTagPatternPattern = Pattern.compile(nextTagPattern);
						Matcher nextTagMatcher = nextTagPatternPattern.matcher(htmlText);
						if(nextTagMatcher.find()){
							int nextTagStartIndex = nextTagMatcher.start();
							resultStr+=htmlText.substring(0,nextTagStartIndex);
							htmlText.delete(0,nextTagStartIndex);
						}
					}
				}
			//if the currentTag is closing.
			}else{
				myStack.pop();//if the currentTag is closing, pop() its correspoding openTag from Stack.
				/*if tagQueue is not empty, it means there is at least one more open tag is in mystack, and 
				its conresponding closing tag still havent be handled. so we use the HtmlTag nextTag to parse it.
				*/		
				if(!tagQueue.isEmpty()){
					HtmlTag nextTag = tagQueue.peek();
					/*if the nextTag is closing tag or no more tag, find current tag's '>', becuase
					the nextTag's corresponding color tag will be insert reight after the currentTag. */
					if(!nextTag.isOpenTag()){
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							//insert color tag right after the '>' of currentTag.
							//String str =  currentTag.isOpenTag()?"Opne"+myStack.peek().colorMatch():"Closeing"+myStack.peek().colorMatch();
							htmlText.insert(currentTagEndIndex,myStack.peek().colorMatch());
							resultStr+=htmlText.substring(0,currentTagEndIndex+myStack.peek().colorMatch().length());
							htmlText.delete(0,currentTagEndIndex+myStack.peek().colorMatch().length());
						}
					}else{
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							resultStr+=htmlText.substring(0,currentTagEndIndex);
							htmlText.delete(0,currentTagEndIndex);
						}
					}
				//if current tag is the last one.
				}else{
					resultStr+=htmlText;
				}
			}
		}
		return resultStr;
	}
}    


