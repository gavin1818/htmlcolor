import java.util.*;
import java.lang.*;
import java.io.*;

public class Test1{
	public static void main(String arg[]){
		String INPUT_FILE = "test3.html";
		String pageText = "";
        try {
            pageText = HighlighterMain.readCompleteFileOrURL( INPUT_FILE );
            Queue<HtmlTag> tags = HtmlTag.tokenize( pageText );
            Highlighter highlighter = new Highlighter(pageText,tags);
            System.out.println(highlighter.highlight());//test
            
        } catch ( FileNotFoundException fnfe ) {
            System.out.println( "Web page or file not found: ");
        } catch ( IOException ioe ) {
            System.out.println( "I/O error: " + ioe.getMessage() );
        }		
	}
}