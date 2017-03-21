
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Queue;
import java.util.regex.*;

public class HighlighterMain {
	public static void main(String arg[]) throws IOException{
		String pageText = "";
		Scanner console = new Scanner( System.in );
        String choice = "s";
        String url="";
		while(true){
			if(choice.startsWith("s")){
				System.out.print("file name(blank for empty): ");
				url = console.nextLine().trim();
                if ( url.length() > 0 ) {
                    try {
		                pageText = readCompleteFileOrURL( url );
		                Queue<HtmlTag> tags = HtmlTag.tokenize( pageText );
		                Highlighter highlighter = new Highlighter(pageText,tags);
		                System.out.println(highlighter.highlight());//test
		                
		            } catch ( FileNotFoundException fnfe ) {
		                System.out.println( "Web page or file not found: "
		                        + url );
		            } catch ( IOException ioe ) {
		                System.out.println( "I/O error: " + ioe.getMessage() );
		            }
             	}			
			}else if ( choice.startsWith( "p" ) ) {
                System.out.println( pageText );
            }else if ( choice.startsWith( "v" ) ) {
            	if ( url.length() > 0 ) {
	                pageText = readCompleteFileOrURL( url );
	                Queue<HtmlTag> tags = HtmlTag.tokenize( pageText );
	                Highlighter highlighter = new Highlighter(pageText,tags);
	            	System.out.println(highlighter.highlight());
	            }
            }else if ( choice.startsWith( "q" ) ) {
                break;
            }
            System.out.println();
            System.out.print( "(v)alidate, (s)et URL, (p)rint, (q)uit? " );
            choice = console.nextLine().trim().toLowerCase();
		}
	}
	
    public static InputStream getInputStream( String address )
            throws IOException {
        return new FileInputStream( address );
    }
    public static String readCompleteFileOrURL( String address ) throws IOException {
        InputStream stream = getInputStream( address ); // open file

        // read each letter into a buffer
        StringBuffer buffer = new StringBuffer();
        while ( true ) {
            int ch = stream.read();
            if ( ch < 0 ) {
                break;
            }

            buffer.append( (char) ch );
        }

        return buffer.toString();
    }   

} 
