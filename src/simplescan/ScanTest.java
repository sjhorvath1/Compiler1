
package simplescan;

import java.io.File;

/**
 * ScanTest tests an input file by iterating through its tokens.
 * 
 * This iterates through the tokens in the file until the first
 * error is found.
 * 
 * @author Sam Horvath
 */
public class ScanTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a Scanner and point it at a file
        Scanner s = new Scanner( new File( "input.txt"));
        // Call nextToken() once
        boolean thereIsAToken = true;
        // While nextToken is true, print the tokens
        while( thereIsAToken) {
            thereIsAToken = s.nextToken();
            if( thereIsAToken) {
                System.out.println("Found " + s.getToken() + " with lexeme " + s.getLexeme());
            }
            else {
                System.out.println("Didn't find token with lexeme " + s.getLexeme());
            }
        }
        
    }
    
}
