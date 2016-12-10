
package parser;

/**
 * Tests the simple version of the parser.
 * @author erik
 */
public class ParserTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "happypath5.pas";
        ///ExpressionParser ep = new ExpressionParser( filename);
        //ep.exp();
        PascalParser pp = new PascalParser(filename);
        pp.program();
        // Should check for remaining tokens in input file here.
        System.out.println("Parse of " + filename + " Successful");
    }
    
}
