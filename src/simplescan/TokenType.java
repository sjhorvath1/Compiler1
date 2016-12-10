
package simplescan;

/**
 * Represents the various types of Tokens in mini-Pascal.
 * @author Sam Horvath
 */
//Is there an incorrect name to name these things? 
//Or do I just get to pick what I want?
//TODO: Add DOT, COLON, COMMA
public enum TokenType {
    PROGRAM,
    INTEGER,
    REAL,
    VAR,
    IF,
    WHILE,
    FUNCTION,
    PROCEDURE,
    BEGIN,
    END,
    THEN,
    DO,
    ELSE,
    ID,
    OF,
    DIV,
    MOD,
    AND,
    NOT,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    LEFT_PARENTHESES,
    RIGHT_PARENTHESES,
    LEFT_CURLY,
    RIGHT_CURLY,
    PLUS,
    EQUALS,
    EXCLAMATION,
    MINUS,
    MULTIPLY,
    DIVIDE,
    SEMICOLON,
    COLON_EQUALS,
    LESS_THAN,
    GREATER_THAN,
    GREATER_THAN_EQUALS,
    LESS_THAN_EQUALS,
    NOT_EQUAL,
    NUMBER, 					//Any valid number
    DOT,
    COLON,
    COMMA,
    ARRAY,
    //OR
    
    
}
