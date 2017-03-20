/*
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HtmlNode{
	private final String element;
	private final String type;
	public HtmlNode(String element, String type){
		this.element = element.toLowerCase();
		this.type = type.toLowerCase();
	}
	public static LinkedList<HtmlNode> tokenize(String text){
		String buf = new StringBuffer(text);
		LinkedList<HtmlNode> queue = new LinkedList<HtmlNode>();
		while(true){
			if(nextNode==null)
				break;
			else
				queue.add(nextNode);
		}
		return queue;
	} 
	private static HtmlNode nextNode(String buf){

	}
}