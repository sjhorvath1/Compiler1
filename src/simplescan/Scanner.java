
package simplescan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * Scanner encompasses a scanner for the mini-Pascal language.
 * 
 * @author Sam Horvath
 */
public class Scanner {
    
    //// Class constants
    private final int START = 0;
    private final int IN_ID_OR_KEYWORD = 1;
    private final int BIG_SYMBOL_HALFWAY = 2;
    private final int COMMENT = 3;
    private final int LESS_THAN_HALFWAY = 4;
    private final int DIGIT_INTERMEDIATE = 5;
    private final int DECIMAL_INTERMEDIATE = 6;
    private final int DECIMAL_DIGITS = 7;
    private final int EXPONENT_INTERMEDIATE = 8;
    private final int EXPONENT_DIGIT = 9;
    private final int EXPONENT_SYMBOL = 10;
    private final int ERROR = 100;
    private final int ID_COMPLETE = 101;
    private final int SYMBOL_COMPLETE = 102;
    private final int SHORT_SYMBOL_COMPLETE = 102;
    private final int NUMBER_ACCEPT = 103;
    
    
    
    
    //// Instance Variables
    
    private TokenType type;
    
    private String lexeme;
    
    private PushbackReader input;
    
    private LookupTable lookup = new LookupTable();
    
    /////  Constructor
    
    /**
     * Creates a Scanner based on an input file.
     * @param inputFile The file to read.
     */
    public Scanner( File inputFile) {
        
        FileReader fr = null;
        try {
            fr = new FileReader(inputFile);
        }
        catch( FileNotFoundException fnfe) {
            System.out.println("Can't find file " + inputFile + ".");
            System.exit(1);
        }
        this.input = new PushbackReader( fr);
    }
    
    
    //// Methods
    
    /**
     * Finds the next token in the input stream.
     * Finds the next token in the attached input stream by
     * implementing a state machine.
     * When the end of file is encountered, this will return
     * false setting both the lexeme and token type to null.
     * When an illegal character or other error is encountered,
     * this will return false setting the token type to null but
     * setting the lexeme to the most recently constructed lexeme.
     * @return true if a token is found, false otherwise.
     */
    public boolean nextToken() {
        int stateNumber = 0;
        String currentLexeme = "";
        int currentCharacter = 0;
        
        while( stateNumber < ERROR) {
            try {
                currentCharacter = input.read();
            }
            catch( IOException ioe) {
            	System.out.println("input stream closed without warning, does your file still exist?");
            }
            if( currentCharacter == -1 && stateNumber != START) {
                currentCharacter =  ' ';
            }
            switch( stateNumber) {
                case START:
                	if(currentCharacter == -1){
                		this.type = null;
                		this.lexeme = null;
                		return(false);
                	}
                    
                    //if the character is a letter, we are in the ID or Keyword State.
                    //add the character to the current lexeme
                    else if( Character.isLetter( currentCharacter)) {
                        stateNumber = IN_ID_OR_KEYWORD;
                        currentLexeme += (char)currentCharacter;
                    }
                    //if the character is whitespace, we loop back to state 0
                    else if ( Character.isWhitespace( currentCharacter)) {
                        stateNumber = START;
                    }
                    //if the character is a + or - sign, then we are in symbol complete.
                    //add the symbol to the current lexeme
                    else if( currentCharacter == '+' ||
                             currentCharacter == '-' ||
                             currentCharacter == '=' ||
                             currentCharacter == '!' ||
                             currentCharacter == '/' ||
                             currentCharacter == '*' ||
                             currentCharacter == ';' ||
                             currentCharacter == '(' ||
                             currentCharacter == ')' ||
                             currentCharacter == '[' ||
                             currentCharacter == ']' ||
                             currentCharacter == '.' ||
                             currentCharacter == ',' 
                             
                    		) {
                        stateNumber = SYMBOL_COMPLETE;
                        currentLexeme += (char)currentCharacter;
                    }
                    //
                    else if( currentCharacter == '>' || currentCharacter == ':') {
                        stateNumber = BIG_SYMBOL_HALFWAY;
                        currentLexeme += (char)currentCharacter;
                    }
                    
                    else if( currentCharacter == '{') {
                        stateNumber = COMMENT;
                    }
                    else if( currentCharacter == '<') {
                    	currentLexeme += (char)currentCharacter;
                    	stateNumber = LESS_THAN_HALFWAY;
                    }
                    else if(Character.isDigit(currentCharacter)){
                    	currentLexeme += (char)currentCharacter;
                    	stateNumber = DIGIT_INTERMEDIATE;
                    }
                    else {
                        currentLexeme += (char)currentCharacter;
                        stateNumber = ERROR;
                    }
                    break;
                case IN_ID_OR_KEYWORD:
                    if( currentCharacter == -1) {
                        stateNumber = ID_COMPLETE;                        
                    }
                    else if( Character.isLetterOrDigit( currentCharacter)) {
                        currentLexeme += (char)currentCharacter;                        
                    }
                    else {
                        try {
                            input.unread( currentCharacter);
                        }
                        catch( IOException ioe){
                        	System.out.println("input stream closed without warning, does your file still exist?");
                        }
                        stateNumber = ID_COMPLETE;
                    }
                    break;
                case BIG_SYMBOL_HALFWAY:
                    if( currentCharacter == '=') {
                        stateNumber = SYMBOL_COMPLETE;
                        currentLexeme += (char)currentCharacter;                        
                    }
                    else {
                        try {
                            input.unread( currentCharacter);
                        }
                        catch( IOException ioe){
                        	System.out.println("input stream closed without warning, does your file still exist?");
                        }
                        stateNumber = SHORT_SYMBOL_COMPLETE;
                    }
                    break;
                case COMMENT:
                    if( currentCharacter == '{') {
                        currentLexeme += (char)currentCharacter;
                        stateNumber = ERROR;
                    }
                    else if(currentCharacter == '}') {
                        stateNumber = START;
                    }
                    else {
                        // Stay in the comment state 3
                    }
                    break;
                case LESS_THAN_HALFWAY:
                	if( currentCharacter == '>' || currentCharacter == '='){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = SYMBOL_COMPLETE;
                	}
                	else{
                		try {
							input.unread(currentCharacter);
						} catch (IOException e) {
							System.out.println("reached end of file");
						}
                		stateNumber = SHORT_SYMBOL_COMPLETE;
                	
                	}
                	break;
                	
                //halfway through a digit being created
                //can add digits endlessly at this point
                case DIGIT_INTERMEDIATE: 
                	if( Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                		//still in digit intermediate
                	}
                	// if a decimal is found, then we go to a new state
                	else if(currentCharacter == '.'){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = DECIMAL_INTERMEDIATE;
                	}
                	else if(currentCharacter == 'E'){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = EXPONENT_INTERMEDIATE;
                	}
                	else{
                		try {
							input.unread(currentCharacter);
							stateNumber = NUMBER_ACCEPT;
						} catch (IOException e) {
							e.printStackTrace();
						}
                	}	
                 break;
                 
                case DECIMAL_INTERMEDIATE:
                	if(Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = DECIMAL_DIGITS;
                	}
                	else{
                		stateNumber = ERROR;
                	}
                break;
                
                case DECIMAL_DIGITS:
                	if(Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                	}
                	else if(currentCharacter == 'E'){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = EXPONENT_INTERMEDIATE;
                	}
                	else{
                		try {
							input.unread(currentCharacter);
						} catch (IOException e) {
							e.printStackTrace();
						}
                		stateNumber = NUMBER_ACCEPT;
                	}
                	break;
                case EXPONENT_INTERMEDIATE:
                	if(Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = EXPONENT_DIGIT;
                	}
                	else if(currentCharacter == '+' || currentCharacter == '-'){
                		stateNumber = EXPONENT_SYMBOL;
                	}
                	else{
                		stateNumber = ERROR;
                	}
                break;
                
                case EXPONENT_SYMBOL:
                	if(Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                		stateNumber = EXPONENT_DIGIT;
                	}
                	else{
                		stateNumber = ERROR;
                	}
                break;
                
                case EXPONENT_DIGIT:
                	if(Character.isDigit(currentCharacter)){
                		currentLexeme += (char)currentCharacter;
                	}
                	else{
                		try {
							input.unread(currentCharacter);
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
                		stateNumber = NUMBER_ACCEPT;
                	}
           
                	
            } // end switch 
        } // end while
        //Final States
        this.lexeme = currentLexeme;
        if( stateNumber == ERROR) {
            this.type = null;
            System.out.println("ERROR scanning "+this.lexeme+", invalid token");
            return( false);
        }
        else if( stateNumber == ID_COMPLETE) {
            this.type = lookup.get( this.lexeme);
            if( this.type == null) {
                this.type = TokenType.ID;
            }
            return( true);
        }
        else if( stateNumber == SYMBOL_COMPLETE) {
            this.type = lookup.get( this.lexeme);
            return( true);
        }
        else if( stateNumber == NUMBER_ACCEPT){
        	this.type = TokenType.NUMBER;
        	return(true);
        }
       
        return( false);
    }
    
    /** 
     * Returns the token type.
     * @return The token type of the most recent token.
     */
    public TokenType getToken() { return this.type;}
    
    /**
     * Returns the lexeme.
     * @return The lexeme of the most recent token.
     */
    public String getLexeme() { return this.lexeme;}
    
    
    
}
