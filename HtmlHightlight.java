//input the file.html from console.
//regular expression find the html tag.
//generate the color text(\color[RED]), and insert it in front of the tag.
//output the new html file.
/*if only text in the tag, only insert color in front of the open tag, 
if there is also other tags in the tag,insert the color at both open and close tags.

*/

/*
define open tag;
define close tag;
define single tag;
boolean onlyText=true;
int num=0;
iterater throngh the html as string{
	if (ele==open tag)
		ele.push();
	else if (ele==self-closing tag)
		insert color in front of it;
		ele.push();
	else if(ele==text)
		ele.push();
	else if (ele==close tag)
		while(next ele is not its open tag)
			if(next ele==text)
				onlyText=true;
				num+=1;
				ele.pop();
			else
				onlyText=false;
				num+=1;
				ele.pop();
			

		if(onlyText==true&&num<=1){
			insert a color in its open tag;
			num=0;
			onlyText=true;
		else
			insert a color in both the close and its open tag;
			num=0;
			onlyText=true;
		}
		pop the close and the open tags; 
}
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
public class HTMLTagValidator{
	private Pattern openPattern;
	private Pattern closePattern;
   	private Matcher matcher;
	private static final String HTML_START_TAG_PATTERN = "<(!DOCTYPE|a|abbr|  
          acronym|address|applet|area|article|aside|audio|b|base|basefont|bdi|bdo|big|blockquote|body|br
          |button|canvas|caption|center|cite|code|col|colgroup|command|datalist|dd|del|details|dfn|dialog
          |dir|div|dl|dt|em|embed|fieldset|figcaption|figure|font|footer|form|frame|frameset
          |h1|h2|h3|h4|h5|h6|head|header|hr|html|i|iframe|img|input|ins|kbd|keygen|label|legend|li|link|  
          map|mark|menu|meta|meter|nav|noframes|noscript|object|ol|optgroup|option|  
          output|p|param|pre|progress|q|rp|rt|ruby|s|samp|script|section|select|small|  
          source|span|strike|strong|style|sub|summary|sup|table|tbody|td|textarea|  
          tfoot|th|thead|time|title|tr|track|tt|u|ul|var|video|wbr)[^>]*>";
  	private static final String HTML_END_TAG_PATTERN ="</(!DOCTYPE|a|abbr|  
          acronym|address|applet|area|article|aside|audio|b|base|basefont|bdi|bdo|big|blockquote|body|br
          |button|canvas|caption|center|cite|code|col|colgroup|command|datalist|dd|del|details|dfn|dialog
          |dir|div|dl|dt|em|embed|fieldset|figcaption|figure|font|footer|form|frame|frameset
          |h1|h2|h3|h4|h5|h6|head|header|hr|html|i|iframe|img|input|ins|kbd|keygen|label|legend|li|link|  
          map|mark|menu|meta|meter|nav|noframes|noscript|object|ol|optgroup|option|  
          output|p|param|pre|progress|q|rp|rt|ruby|s|samp|script|section|select|small|  
          source|span|strike|strong|style|sub|summary|sup|table|tbody|td|textarea|  
          tfoot|th|thead|time|title|tr|track|tt|u|ul|var|video|wbr)[^>]*>";
    private static final  String HTML_SINGLE_TAG_PATTERN = "<[^>]+>";
	public HTMLTagValidator(){//constructor
	  openPattern = Pattern.compile(HTML_START_TAG_PATTERN);
	  closePattern = Pattern.compile(HTML_END_TAG_PATTERN);
	  singlePattern = Pattern.compile(HTML_SINGLE_TAG_PATTERN);
	}

  	public boolean validate(String tag){
	  matcher = pattern.matcher(tag);
	  return matcher.matches();
  	}
  	public void insertColor(){
  		Stack symbolStack = new Stack();

  	}
}
*/
public class HtmlHightlight
{	 
	public static void main(String [] args) 
	{	
		final String HTML_TAG_PATTERN = "<([/]?)([a-zA-Z]+)([0-9!-]*)([^>]*)>";
		//inport string from System.console
		//
        Pattern pattern = Pattern.compile(HTML_TAG_PATTERN);
		//System.out.println(HTML_START_TAG_PATTERN);
		String htmlText = "<h1>dfdsfdf</h1>";
		Matcher tag =pattern.matcher(htmlText);
		//Matcher singleTag =singlePattern.matcher(htmlText);
		//System.out.println(openTag.matches);
		while(tag.find()){
			System.out.println(tag.group());
		}
	}
}