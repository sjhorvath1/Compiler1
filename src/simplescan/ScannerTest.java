
package simplescan;

import java.io.File;

/**
 * A unit test based on the ScannerTestCorrect.txt input file.
 * @author Sam Horvath
 */
public class ScannerTest {
    public static void main(String[] args) {
        // Create a Scanner and point it at a file
        // In ScannerTestCorrect, there should be the entire keyword and symbol list
        Scanner s = new Scanner( new File( "ScannerTestCorrect.txt"));
        
        // This should be the program token
        boolean hasToken = s.nextToken();
        TokenType token = s.getToken();
        String lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for program";
        assert token == TokenType.PROGRAM : "No token type for program";
        assert lexeme.equals( "program") : "No lexeme for program";
        
        //This should be the integer token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for integer";
        assert token == TokenType.INTEGER : "No token type for integer";
        assert lexeme.equals( "integer") : "No lexeme for integer";
        
        // This should be the real token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for real";
        assert token == TokenType.REAL : "No token type for real";
        assert lexeme.equals( "real") : "No lexeme for real";
        
        // This should be the var token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for var";
        assert token == TokenType.VAR : "No token type for var";
        assert lexeme.equals( "var") : "No lexeme for var";
        
     // This should be the if token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for if";
        assert token == TokenType.IF : "No token type for if";
        assert lexeme.equals( "if") : "No lexeme for if";
        
     // This should be the while token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for while";
        assert token == TokenType.WHILE : "No token type for while";
        assert lexeme.equals( "while") : "No lexeme for while";
        
     // This should be the function token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for function";
        assert token == TokenType.FUNCTION : "No token type for function";
        assert lexeme.equals( "function") : "No lexeme for function";
        
     // This should be the procedure  token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for procedure";
        assert token == TokenType.PROCEDURE : "No token type for procedure";
        assert lexeme.equals( "procedure") : "No lexeme for procedure";
        
     // This should be the begin token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for begin";
        assert token == TokenType.BEGIN : "No token type for begin";
        assert lexeme.equals( "begin") : "No lexeme for begin";
        
     // This should be the end token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for end";
        assert token == TokenType.END : "No token type for end";
        assert lexeme.equals( "end") : "No lexeme for end";
        
     // This should be the then token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for then";
        assert token == TokenType.THEN : "No token type for then";
        assert lexeme.equals( "then") : "No lexeme for then";
        
     // This should be the do token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for do";
        assert token == TokenType.DO : "No token type for do";
        assert lexeme.equals( "do") : "No lexeme for do";
        
     // This should be the else token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for else";
        assert token == TokenType.ELSE : "No token type for else";
        assert lexeme.equals( "else") : "No lexeme for else";
        

        
     // This should be the ID token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for id";
        assert token == TokenType.ID : "No token type for id";
        assert lexeme.equals( "id") : "No lexeme for id";
        
     // This should be the of token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for of";
        assert token == TokenType.OF : "No token type for of";
        assert lexeme.equals( "of") : "No lexeme for of";
        
     // This should be the div token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for div";
        assert token == TokenType.DIV : "No token type for div";
        assert lexeme.equals( "div") : "No lexeme for div";
        
     // This should be the mod token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for mod";
        assert token == TokenType.MOD : "No token type for mod";
        assert lexeme.equals( "mod") : "No lexeme for mod";
        
     // This should be the not token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for not";
        assert token == TokenType.NOT : "No token type for not";
        assert lexeme.equals( "not") : "No lexeme for not";
        
     // This should be the [ token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for [";
        assert token == TokenType.LEFT_BRACKET : "No token type for left bracket";
        assert lexeme.equals( "[") : "No lexeme for left bracket";
        
     // This should be the ] token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for ]";
        assert token == TokenType.RIGHT_BRACKET : "No token type for right bracket";
        assert lexeme.equals( "]") : "No lexeme for right bracket";
        
     // This should be the ( token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for (";
        assert token == TokenType.LEFT_PARENTHESES : "No token type for left parentheses";
        assert lexeme.equals( "(") : "No lexeme for left parentheses";
        
        // This should be the ) token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for )";
        assert token == TokenType.RIGHT_PARENTHESES : "No token type for right parentheses";
        assert lexeme.equals( ")") : "No lexeme for right parentheses";
        
        // This should be the { token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        //If we get a + in the following token then we are reading comments correctly
        // Test to make sure these values are correct
        assert hasToken : "No token for {";
        assert token == TokenType.PLUS: "No token type for left curly";
        assert lexeme.equals( "+") : "No lexeme for left curly bracket";
        
        // This should be the } token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for }";
        assert token == TokenType.EQUALS : "No token type for right curly";
        assert lexeme.equals( "=") : "No lexeme for right curly";
        
//        // This should be the + token
//        hasToken = s.nextToken();
//        token = s.getToken();
//        lexeme = s.getLexeme();
//        
//        // Test to make sure these values are correct
//        assert hasToken : "No token for +";
//        assert token == TokenType.PLUS : "No token type for plus";
//        assert lexeme.equals( "+") : "No lexeme for plus";
//        
//        // This should be the = token
//        hasToken = s.nextToken();
//        token = s.getToken();
//        lexeme = s.getLexeme();
//        
//        // Test to make sure these values are correct
//        assert hasToken : "No token for =";
//        assert token == TokenType.EQUALS : "No token type for equals";
//        assert lexeme.equals( "=") : "No lexeme for equals";
        
     // This should be the ! token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for !";
        assert token == TokenType.EXCLAMATION : "No token type for exclamation point";
        assert lexeme.equals( "!") : "No lexeme for exclamation";
        
     // This should be the - token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for -";
        assert token == TokenType.MINUS : "No token type for minus";
        assert lexeme.equals( "-") : "No lexeme for minus";
        
     // This should be the * token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for *";
        assert token == TokenType.MULTIPLY : "No token type for multiply";
        assert lexeme.equals( "*") : "No lexeme for multiply";
        
        
     // This should be the / token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for /";
        assert token == TokenType.DIVIDE : "No token type for divide";
        assert lexeme.equals( "/") : "No lexeme for divide";
        
     // This should be the ; token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for ;";
        assert token == TokenType.SEMICOLON : "No token type for semi colon";
        assert lexeme.equals( ";") : "No lexeme for semi colon";
        
     // This should be the := token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for :=";
        assert token == TokenType.COLON_EQUALS : "No token type for colon equals";
        assert lexeme.equals( ":=") : "No lexeme for colon equals";
        
     // This should be the < token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for <";
        assert token == TokenType.LESS_THAN : "No token type for less than";
        assert lexeme.equals( "<") : "No lexeme for less than";
        
     // This should be the > token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for >";
        assert token == TokenType.GREATER_THAN : "No token type for greater than";
        assert lexeme.equals( ">") : "No lexeme for greater than";
        
     // This should be the >= token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for >=";
        assert token == TokenType.GREATER_THAN_EQUALS : "No token type for greater than or equal to";
        assert lexeme.equals( ">=") : "No lexeme for greater than or equal to";
        
    // This should be the <= token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for <=";
        assert token == TokenType.LESS_THAN_EQUALS : "No token type for less than or equal to";
        assert lexeme.equals( "<=") : "No lexeme for less than or equal to";
        
     // This should be the <> token
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken : "No token for <>";
        assert token == TokenType.NOT_EQUAL : "No token type for not equal";
        assert lexeme.equals( "<>") : "No lexeme for not equal";
        
        
        // What happens when we hit the end of the file?
        hasToken = s.nextToken();
        token = s.getToken();
        lexeme = s.getLexeme();
        
        // Test to make sure these values are correct
        assert hasToken == false : "No token for EOF";
        assert token == null : "No token type for EOF";
        assert lexeme == null : "No lexeme for EOF";
        
    }
}
