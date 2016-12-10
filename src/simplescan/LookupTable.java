package simplescan;

import java.util.Hashtable;

/**
 * LookupTable provides a lookup for all symbols and keywords in
 * the mini-Pascal language.
 * @author Sam Horvath
 */
public class LookupTable extends Hashtable<String,TokenType> {
    
    public LookupTable()  {
        
        // Keywords
        this.put("program", TokenType.PROGRAM);
        this.put("integer", TokenType.INTEGER);
        this.put("real", TokenType.REAL);
        this.put("var", TokenType.VAR);
        this.put("if",TokenType.IF);
        this.put("while",TokenType.WHILE);
        this.put("function", TokenType.FUNCTION);
        this.put("procedure", TokenType.PROCEDURE);
        this.put("begin", TokenType.BEGIN);
        this.put("end",TokenType.END);
        this.put("then", TokenType.THEN);
        this.put("do", TokenType.DO);
        this.put("else", TokenType.ELSE);
        this.put("of", TokenType.OF);
        this.put("div", TokenType.DIV);
        this.put("mod", TokenType.MOD);
        this.put("not", TokenType.NOT);
        this.put("array", TokenType.ARRAY);
        //this.put("or", TokenType.OR);
        // Symbols
        this.put("[", TokenType.LEFT_BRACKET);
        this.put("]", TokenType.RIGHT_BRACKET);
        this.put("(", TokenType.LEFT_PARENTHESES);
        this.put(")", TokenType.RIGHT_PARENTHESES);
        this.put("{", TokenType.LEFT_CURLY);
        this.put("}", TokenType.RIGHT_CURLY);
        this.put( "+", TokenType.PLUS);
        this.put("=", TokenType.EQUALS);
        this.put("!", TokenType.EXCLAMATION);
        this.put( "-", TokenType.MINUS);
        this.put("*", TokenType.MULTIPLY);
        this.put("/", TokenType.DIVIDE);
        this.put(";", TokenType.SEMICOLON);
        this.put(":=", TokenType.COLON_EQUALS);
        this.put("<", TokenType.LESS_THAN);
        this.put( ">", TokenType.GREATER_THAN);
        this.put( ">=", TokenType.GREATER_THAN_EQUALS);
        this.put("<=", TokenType.LESS_THAN_EQUALS);
        this.put("<>", TokenType.NOT_EQUAL);
        this.put(".", TokenType.DOT);
        this.put(":", TokenType.COLON);
        this.put(",", TokenType.COMMA);
        
    }
}
