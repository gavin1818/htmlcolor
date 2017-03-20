import java.util.*;
import java.lang.StringBuilder;
import java.util.regex.*;

public class Test{
	public static void main(String arg[]){
		String str ="<div>abcd<div>efghigk";
		String insertStr = "GAVINCAI";
		int s, e; //Java里 int 值不需要initial
		StringBuilder sb = new StringBuilder(str);
		String currentTagPattern = "<[ ]*"+"div"+"[^>]*>";
		Pattern currentTagPatternPattern = Pattern.compile(currentTagPattern);
		Matcher currentTagMatcher = currentTagPatternPattern.matcher(sb);
		while(currentTagMatcher.find()){
			s = currentTagMatcher.start();
			e = currentTagMatcher.end();
			System.out.println("Original String: "+sb);
			System.out.println("start is: "+s);
			System.out.println("end is: "+e);

			System.out.println("subString insert之前 : "+sb.substring(s,e));
			sb.insert(s,insertStr);
			s = currentTagMatcher.start();
			e = currentTagMatcher.end();
			System.out.println("after the insert: "+sb);
			System.out.println("subString insert之后: "+sb.substring(s,e));
			System.out.println("remove before: "+sb.substring(0,s));
			System.out.println("remove after: "+sb.substring(e));
			
		}
	}
}