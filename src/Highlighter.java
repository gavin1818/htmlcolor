import java.util.*;
import java.io.*;
import java.lang.StringBuilder;
import java.util.regex.*;

public class Highlighter {
 	private Queue<HtmlTag> tagQueue;    
    private StringBuilder htmlText = new StringBuilder();  

    public Highlighter(String str, Queue<HtmlTag> tagQueue) throws IllegalArgumentException{
    	htmlText = new StringBuilder(str);
    	this.tagQueue=tagQueue;
    }

    public String highlight() {
    	StringBuilder resultStr = new StringBuilder();
    	/*myStack is only used to store openTag(no self-closing tag)*/
    	TagStack myStack = new TagStack();
    	while(!tagQueue.isEmpty()){
    		//tagQueue contains all tags(open,close,selfclose) from the tokenized html.
    		HtmlTag currentTag=tagQueue.poll();
			String currentTagPattern = "<[ ]*[/]?[ ]*"+currentTag.getElement()+"[^>]*>[\n\t ]*";
			Pattern currentTagPatternPattern = Pattern.compile(currentTagPattern);
			Matcher currentTagMatcher = currentTagPatternPattern.matcher(htmlText);
    		if(currentTag.isOpenTag()){
    			if(!currentTag.isSelfClosing()){
    				//if tag is not slefClose && not closing tag, push it on myStack. 
    				myStack.push(currentTag);
    			}
				if(currentTagMatcher.find()){
					//find the first index of '<' in the current tag.
					int currentTagStartIndex = currentTagMatcher.start();
					htmlText.insert(currentTagStartIndex, currentTag.colorMatch());
					//find the last index of the current tag.
					int currentTagEndIndex = currentTagMatcher.end(); //end(): return ending index+1 of the matcher. 
					resultStr.append(htmlText.substring(0,currentTagEndIndex+currentTag.colorMatch().length()));//substring(): The ending index is exclusive.
					htmlText.delete(0,currentTagEndIndex+currentTag.colorMatch().length());//delete():The ending index is exclusive.
					/*find find the last index of the current tag and add it into resultStr, 
					and remove it from htmlText */
					//find the next tag's index, move all text sequnece before the tag into the resultStr.
					if(!tagQueue.isEmpty()){
						HtmlTag nextTag = tagQueue.peek();
	    				String nextTagPattern = "<[ ]*[/]?[ ]*"+nextTag.getElement()+"[^>]*>";
						Pattern nextTagPatternPattern = Pattern.compile(nextTagPattern);
						Matcher nextTagMatcher = nextTagPatternPattern.matcher(htmlText);
						if(nextTagMatcher.find()){
							int nextTagStartIndex = nextTagMatcher.start();
							resultStr.append(htmlText.substring(0,nextTagStartIndex));
							htmlText.delete(0,nextTagStartIndex);
						}
					}
				}
			//if the currentTag is closing.
			}else{
				/*pop() its correspoding openTag from Stack.
				if tagQueue is not empty, it means there is at least one more open tag is in mystack, and 
				its conresponding closing tag still havent be processd. so we use the HtmlTag nextTag to parse it.
				*/		
				myStack.pop();
				if(!tagQueue.isEmpty()){
					HtmlTag nextTag = tagQueue.peek();
					/*if the nextTag is closing tag, find the last index of the currentTag, becuase
					the nextTag's corresponding color tag will be insert after the currentTag. */
					if(!nextTag.isOpenTag()){
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							htmlText.insert(currentTagEndIndex,myStack.peek().colorMatch());
							resultStr.append(htmlText.substring(0,currentTagEndIndex+myStack.peek().colorMatch().length()));
							htmlText.delete(0,currentTagEndIndex+myStack.peek().colorMatch().length());
						}
					}else{
						if(currentTagMatcher.find()){
							int currentTagEndIndex = currentTagMatcher.end();
							resultStr.append(htmlText.substring(0,currentTagEndIndex));
							htmlText.delete(0,currentTagEndIndex);
						}
					}
				//if current tag is the last one.
				}else{
					resultStr.append(htmlText);
				}
			}
		}
		return resultStr.toString();
	}
}    


