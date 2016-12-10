package parser;

import simplescan.*;

import symbolTable.IDType;
import symbolTable.NumType;
import symbolTable.SymbolTable;

import java.io.File;
import java.util.Stack;

import SyntaxTree.*;

/**
 * Parser for mini-Pascal
 * @author Sam Horvath
 */
public class PascalParser {

    /** The scanner to supply tokens from the input file. */
    private Scanner scanner;
    private SymbolTable symbols;
    private Stack<String> declarationStack;
    /** The current token for one token look ahead. */
    private TokenType currentToken;
    private Integer arrayStart;
    private Integer arrayEnd;
    private String functionDeclare;
    
    
    /**
     * Creates a pascal parser to parse the named file.
     * @param filename The name of the file to be parsed.
     */
    public PascalParser( String filename) {
        File input = new File( filename);
        scanner = new Scanner( input);
        symbols = new SymbolTable();
        declarationStack = new Stack<String>();
        
        
        // Load in the first token as the lookahead token:
        scanner.nextToken();
        currentToken = scanner.getToken();
        String functionDeclare = null;
    }
    
  
    
    /**
     * Implements program -> program id ;
     *                       declarations
     *                       subprogram_declarations
     *                       compound_statement
     *                       .
     */
    public ProgramNode program() {
    	
        System.out.println("program");
        match(TokenType.PROGRAM);
        ProgramNode programnode = new ProgramNode(scanner.getLexeme());
        symbols.add(scanner.getLexeme(), IDType.PROGRAM, null);
        match(TokenType.ID);
        match(TokenType.SEMICOLON);
        programnode.setVariables(declarations(new DeclarationsNode()));
        programnode.setFunctions(subprogram_declarations(new SubProgramDeclarationsNode()));
        programnode.setMain(compound_statement());
        match(TokenType.DOT);
        System.out.println(symbols.toString());
		return programnode;
    }
    
    
    /**
     * Implements identifier_list -->id | id, identifier_list
     * 
     */
    public void identifier_list() {
    	declarationStack.push(scanner.getLexeme());
		match(TokenType.ID);
		while(currentToken == TokenType.COMMA){
			match(TokenType.COMMA);
			declarationStack.push(scanner.getLexeme());
			match(TokenType.ID);
		}
		
		
	}
    
    /**
     * Implements  part of declarations that is declarations -> lambda.
     * @return 
     */
    public DeclarationsNode declarations(DeclarationsNode decNode) {
        System.out.println("declarations");
        Stack<String> nodeStack = new Stack<String>();
        //DeclarationsNode decNode = new DeclarationsNode();
        if(currentToken == TokenType.VAR){
        	
        	match(TokenType.VAR);
        	identifier_list();
        	nodeStack = (Stack<String>) declarationStack.clone();
        	while(!nodeStack.empty()){
        		decNode.add(new VariableNode(nodeStack.pop()));
        	}
        	match(TokenType.COLON);
        	type();
        	match(TokenType.SEMICOLON);
        	declarations(decNode);
        }
		return decNode;
    }
    
    /**
     * Implements type function
     * type can either be real, integer, or standard array
     * 
     */
    public void type() {
    	System.out.println("type");
    	if(currentToken == TokenType.INTEGER || currentToken == TokenType.REAL){
    		standard_type();
    	}
    	else if(currentToken == TokenType.ARRAY){
    		//TODO:add a token for array
    		match(TokenType.ARRAY);
    		match(TokenType.LEFT_BRACKET);
    		arrayStart = Integer.parseInt(scanner.getLexeme());
    		match(TokenType.NUMBER);
    		match(TokenType.COLON);
    		arrayEnd = Integer.parseInt(scanner.getLexeme());
    		match(TokenType.NUMBER);
    		match(TokenType.RIGHT_BRACKET);
    		match(TokenType.OF);
    		standard_type();
    	}
    	else{
    		System.out.println("Unexpected type error");
    		error();
    	}
		
	}

    /**
     * Implements the standard type function. Type can be either integer or real.
     */
	public void standard_type() {
		if(currentToken == TokenType.INTEGER){
			if(arrayStart != null){
				while(!declarationStack.isEmpty()){
					symbols.add(declarationStack.pop(), IDType.ARRAY, NumType.INTEGER, arrayStart, arrayEnd);
				}
				arrayStart = null;
			}
			else if(functionDeclare != null){
				symbols.add(functionDeclare, IDType.FUNCTION, NumType.INTEGER);
				functionDeclare = null;
			}
			else{
				while(!declarationStack.isEmpty()){
					symbols.add(declarationStack.pop(), IDType.VARIABLE, NumType.INTEGER);
				}
			}
			
			match(TokenType.INTEGER);
		}
		else if(currentToken == TokenType.REAL){
			if(arrayStart != null){
				while(!declarationStack.isEmpty()){
					symbols.add(declarationStack.pop(), IDType.ARRAY, NumType.REAL, arrayStart, arrayEnd);
				}
				arrayStart = null;
			}
			else if(functionDeclare != null){
				symbols.add(functionDeclare, IDType.FUNCTION, NumType.REAL);
				functionDeclare = null;
			}
			else{
				while(!declarationStack.isEmpty()){
					symbols.add(declarationStack.pop(), IDType.VARIABLE, NumType.REAL);
				}
			}
			match(TokenType.REAL);
		}
		
		else{
			System.out.println("standard type error");
			error();
		}
	}


	/**
     * Implements the part of subprogram_declarations that is 
     * subprogram_declarations -> lambda.
	 * @return 
     */
   public SubProgramDeclarationsNode subprogram_declarations(SubProgramDeclarationsNode subNode) {
        System.out.println("subprogram_declarations");
       
        if(currentToken == TokenType.FUNCTION){
        		subNode.add(subprogram_declaration(new FunctionNode()));
        		match(TokenType.SEMICOLON);
        		subprogram_declarations(subNode);
   		}
        else if(currentToken == TokenType.PROCEDURE){
        	subNode.add(subprogram_declaration(new ProcedureNode()));
    		match(TokenType.SEMICOLON);
    		subprogram_declarations(subNode);
        }
        return subNode;
        
    }
    
   /**
    * Implements subprogram_declaration.
    * Subprogram can either be a function or a procedure. 
 * @return 
    */
    public ProcedureNode subprogram_declaration(ProcedureNode procNode) {
		System.out.println("Subprogram_declaration");
		subprogram_head(procNode);
		procNode.setVariables(declarations(procNode.getVariables()));
		procNode.setFunctions(subprogram_declarations(new SubProgramDeclarationsNode()));
		procNode.setMain(compound_statement());
		return procNode;
	}
    
    /*
     * Implements subprogram_head function.
     * 
     */
	private void subprogram_head(ProgramNode progNode) {
		String temp;
		System.out.println("Subprogram_head");
		if(currentToken == TokenType.FUNCTION){
			match(TokenType.FUNCTION);
			progNode.setName(scanner.getLexeme());
			temp = scanner.getLexeme();
			match(TokenType.ID);
			progNode.setVariables(arguments(new DeclarationsNode()));
			
			match(TokenType.COLON);
			functionDeclare = temp;
			standard_type();
			match(TokenType.SEMICOLON);
		}
		else if(currentToken == TokenType.PROCEDURE){
			match(TokenType.PROCEDURE);
			symbols.add(scanner.getLexeme(), IDType.PROCEDURE, null);
			match(TokenType.ID);
			progNode.setVariables(arguments(new DeclarationsNode()));
			match(TokenType.SEMICOLON);
		}
		else{
			System.out.println("subprogram_head error");
			error();
		}
	}

	public DeclarationsNode arguments(DeclarationsNode decNode) {
		System.out.println("arguments");
		if(currentToken == TokenType.LEFT_PARENTHESES){
			match(TokenType.LEFT_PARENTHESES);
			parameter_list(decNode);
			match(TokenType.RIGHT_PARENTHESES);
		}
		return decNode;
		
	}
	
	/**
	 * Implements the parameter list expression. Parameter lists are made up of identifier lists and types. 
	 * @param decNode 
	 */
	public void parameter_list(DeclarationsNode decNode) {
		Stack<String> nodeStack;
		System.out.println("parameter list");
		if(currentToken == TokenType.ID){
			identifier_list();
			nodeStack  = (Stack<String>) declarationStack.clone();
			while(!nodeStack.empty()){
				decNode.add(new VariableNode (nodeStack.pop()));
			}
			match(TokenType.COLON);
			type();
			if(currentToken == TokenType.SEMICOLON){
				match(TokenType.SEMICOLON);
				parameter_list(decNode);
			}
		}
		else{
			System.out.println("parameter list error");
			error();
		}
		
		
	}

	/**
     * Implements
     *   compound_statement -> begin optional_statements end
	 * @return 
     */
    public CompoundStatementNode compound_statement() {
        System.out.println("compound_statement");
        CompoundStatementNode cpNode = new CompoundStatementNode();
        match( TokenType.BEGIN);
        optional_statements(cpNode);
        match( TokenType.END);
        return cpNode;
    }
    
    /**
     * Implements the part of optional_statements that is 
     * optional_statements -> lambda.
     */
    public void optional_statements(CompoundStatementNode cpNode) {
        System.out.println("optional_statements");
        if(currentToken == TokenType.ID || currentToken == TokenType.BEGIN || currentToken == TokenType.IF || currentToken == TokenType.WHILE){
        	statement_list(cpNode);
        }
    }
    
    /**
     * Implements the creation of a single statement or a list of statements.
     */
    public void statement_list(CompoundStatementNode cpNode){
    	System.out.println("Statement List");
    	StatementNode sNode = statement();
    	if( sNode != null){
    		cpNode.addStatement(sNode);
    	}
    	if(currentToken == TokenType.SEMICOLON){
			match(TokenType.SEMICOLON);
			statement_list(cpNode);
		}
    }
    
    /**
     * Implements the statement function, statements can take many forms. 
     * @return 
     */
    public StatementNode statement(){
    	System.out.println("statement");
    	if(currentToken == TokenType.ID){
    		AssignmentStatementNode assNode = new AssignmentStatementNode();
    		String currentID = scanner.getLexeme();
    		if(symbols.isVARIABLE(currentID)){
    			assNode.setlValue(variable());
        		match(TokenType.COLON_EQUALS);
        		assNode.setExpression(expression());
        		return assNode;
    		}
    		else if(symbols.isFUNCTION(currentID) || symbols.isPROCEDURE(currentID)){
    			return procedure_statement();
    		}
    		
    		
    	}
    	else if(currentToken == TokenType.BEGIN){
    		return compound_statement();
    	}
    	else if(currentToken == TokenType.IF){
    		IfStatementNode ifNode = new IfStatementNode();
    		match(TokenType.IF);
    		ifNode.setCondition(expression());
    		match(TokenType.THEN);
    		ifNode.setThenStatement(statement());
    		match(TokenType.ELSE);
    		ifNode.setElseStatement(statement());
    		return ifNode;
    	}
    	else if(currentToken == TokenType.WHILE){
    		WhileStatementNode whileNode = new WhileStatementNode();
    		match(TokenType.WHILE);
    		whileNode.setCondition(expression());
    		match(TokenType.DO);
    		whileNode.setBody(statement());
    	}
    	
    		return null;
    }
    
    /**
     * Implements the variable function, and ID or an ID with an attached expression.
     * @return 
     */
	public VariableNode variable() {
		VariableNode varNode = new VariableNode(scanner.getLexeme());
		if(currentToken == TokenType.ID){
			match(TokenType.ID);
			if(currentToken == TokenType.LEFT_BRACKET){
				match(TokenType.LEFT_BRACKET);
				expression();
				match(TokenType.RIGHT_BRACKET);
			}
		}
		return varNode;
		
	}
	
	/**
	 * Implements procedure statement
	 * @return 
	 */
	public StatementNode procedure_statement() {
		match(TokenType.ID);
		if(currentToken == TokenType.LEFT_PARENTHESES){
			match(TokenType.LEFT_PARENTHESES);
			expression_list();
			match(TokenType.RIGHT_PARENTHESES);
		}
		//TODO: needs a ProcedureCallNode();
		return null;
	}
	
	/**
	 * Implements the expression list function, which allows for one expression or a list of them.
	 */
	public void expression_list(){
		System.out.println("expression_list");
		if(currentToken == TokenType.ID){
			expression();
			if(currentToken == TokenType.COMMA){
				match(TokenType.COMMA);
				expression_list();
			}
		}
		else{
			//System.out.println("Expected a list of IDs, none given");
			error();
		}
	}
	
	/**
	 * Implements the expression--> simple_expression function.
	 */
	public ExpressionNode expression() {
		System.out.println("expression");
		ExpressionNode answer= null;
		if(currentToken == TokenType.ID || currentToken == TokenType.NUMBER || currentToken == TokenType.LEFT_PARENTHESES || currentToken == TokenType.NOT){
			answer = simple_expression();
			//TODO: clean up later, ors, match current token, followed by simple expression
			if(currentToken == TokenType.LESS_THAN){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.LESS_THAN);
				opNode.setRight(simple_expression());
				return opNode;
			}	
			else if(currentToken == TokenType.LESS_THAN_EQUALS){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.LESS_THAN_EQUALS);
				opNode.setRight(simple_expression());
				return opNode;
			}
			else if(currentToken == TokenType.GREATER_THAN){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.GREATER_THAN);
				opNode.setRight(simple_expression());
				return opNode;
			}
			else if(currentToken == TokenType.GREATER_THAN_EQUALS){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.GREATER_THAN_EQUALS);
				opNode.setRight(simple_expression());
				return opNode;
			}
			else if(currentToken == TokenType.NOT_EQUAL){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.NOT_EQUAL);
				opNode.setRight(simple_expression());
				return opNode;
			}
			else if(currentToken == TokenType.EQUALS){
				OperationNode opNode =  new OperationNode(currentToken);
				opNode.setLeft(answer);
				match(TokenType.EQUALS);
				opNode.setRight(simple_expression());
				return opNode;
			}
		}
		return answer;
	}	

	/**
	 * Implements the simple_expression function. 
	 * Simple expressions are made up of a term and a simple part or a sign, a term, and a simple part.
	 */
	public ExpressionNode simple_expression() {
		System.out.println("simple expression");
		 if(currentToken == TokenType.PLUS || currentToken == TokenType.MINUS){
			sign();
			ExpressionNode answer = term();
			if(currentToken  == TokenType.PLUS || currentToken == TokenType.MINUS){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(simple_part());
				return opNode;
				
			}
			return answer;
			
		}
		 else if(currentToken == TokenType.ID || currentToken == TokenType.NUMBER || currentToken == TokenType.LEFT_PARENTHESES || currentToken == TokenType.NOT ){
			 ExpressionNode answer = term();
				if(currentToken  == TokenType.PLUS || currentToken == TokenType.MINUS){
					OperationNode opNode = new OperationNode();
					opNode.setOperation(currentToken);
					opNode.setLeft(answer);
					opNode.setRight(simple_part());
					return opNode;
					
				}
				return answer;
		}
		
		else{
			//System.out.println("Incorrect simple expression");
			error();
		}
		 return null;
	}
	
	/**
	 * Implements the simple part expression, which is made up of a term, and a simple_part recursively if necessary.
	 */
	public ExpressionNode simple_part() {
		System.out.println("simple part");
		if(currentToken == TokenType.PLUS){
			match(TokenType.PLUS);
			ExpressionNode answer = term();
			if(currentToken  == TokenType.PLUS || currentToken == TokenType.MINUS){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(simple_part());
				return opNode;
				
			}
			return answer;
		}
		else if(currentToken == TokenType.MINUS){
			match(TokenType.MINUS);
			ExpressionNode answer = term();
			if(currentToken  == TokenType.PLUS || currentToken == TokenType.MINUS){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(simple_part());
				return opNode;
				
			}
			return answer;
		}
		return null;
		
	}
	
	/**
	 * Implements the term expression. Terms are made up of an addop, a factor and a term part.
	 */
	public ExpressionNode term(){ 
		System.out.println("term");
		ExpressionNode answer = factor();
		//System.err.println("uh oh");
		if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE || currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
			OperationNode opNode = new OperationNode();
			opNode.setOperation(currentToken);
			opNode.setLeft(answer);
			opNode.setRight(term_part());
			return opNode;
		}
		else{
			return answer;
		}
	
	}
	
	/**
	 * Implements the term part expression, term parts are made up ofa mulop, a factor, and a term part recursively if necessary.
	 * @return 
	 */
	public ExpressionNode term_part() {
		System.out.println("term_part");
		if(currentToken == TokenType.MULTIPLY){
			match(TokenType.MULTIPLY);
			ExpressionNode answer = factor();
			
			if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE|| currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(term_part());
				return opNode;
			}
			else{
				return answer;
			}
		}
		else if(currentToken == TokenType.DIVIDE){
			match(TokenType.DIVIDE);
			ExpressionNode answer = factor();
			
			if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE|| currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(term_part());
				return opNode;
			}
			else{
				return answer;
			}
		}
		else if(currentToken == TokenType.DIV){
			match(TokenType.DIV);
			ExpressionNode answer = factor();
			
			if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE|| currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(term_part());
				return opNode;
			}
			else{
				return answer;
			}
		}
		else if(currentToken == TokenType.MOD){
			match(TokenType.MOD);
			ExpressionNode answer = factor();
			
			if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE|| currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(term_part());
				return opNode;
			}
			else{
				return answer;
			}
		}
		else if(currentToken == TokenType.AND){
			match(TokenType.AND);
			ExpressionNode answer = factor();
			//TODO add div, mod, and to currentToken = below
			if (currentToken == TokenType.MULTIPLY || currentToken == TokenType.DIVIDE|| currentToken == TokenType.DIV || currentToken == TokenType.MOD || currentToken == TokenType.AND){
				OperationNode opNode = new OperationNode();
				opNode.setOperation(currentToken);
				opNode.setLeft(answer);
				opNode.setRight(term_part());
				return opNode;
			}
			else{
				return answer;
			}
		}
		return null;
		
	}
	
	/**
	 * Implements the factor expression. Factors have several possible makeups. 
	 */
	public ExpressionNode factor() {
		System.out.println("factor");
		if(currentToken == TokenType.ID){
			String idName = scanner.getLexeme();
			match(TokenType.ID);
			//array access node 
			if(currentToken == TokenType.LEFT_BRACKET){
				match(TokenType.LEFT_BRACKET);
				expression();
				match(TokenType.RIGHT_BRACKET);
			}
			//function call
			else if(currentToken == TokenType.LEFT_PARENTHESES){
				match(TokenType.LEFT_PARENTHESES);
				expression_list();
				match(TokenType.RIGHT_PARENTHESES);
			}
			//return a variable node 
			return new VariableNode(idName);
		}
		else if(currentToken == TokenType.NUMBER){
			String value = scanner.getLexeme();
			match(TokenType.NUMBER);
			return new ValueNode(value);
		}
		else if(currentToken == TokenType.LEFT_PARENTHESES){
			match(TokenType.LEFT_PARENTHESES);
			expression();
			match(TokenType.RIGHT_PARENTHESES);
		}
		else if(currentToken == TokenType.NOT){
			match(TokenType.NOT);
			factor();
		}
		else{
			//System.out.println("factor error");
			error();
		}
		return null;
	}
	
	/**
	 * Implements the sign expression. Signs are made up of + or -
	 */
	public void sign() {
		System.out.println("sign");
		if(currentToken == TokenType.PLUS){
			match(TokenType.PLUS);
		}
		else if(currentToken == TokenType.MINUS){
			match(TokenType.MINUS);
		}
		else{
			//System.out.println("incorrect sign error");
			error();
		}
		
	}


	

	/**
     * Matches a token.
     * Matches the given token against the current token. Loads the next
     * token from the input file into the current token.
     * @param expectedToken The token to match.
     */
    public void match( TokenType expectedToken) {
        System.out.println("match " + expectedToken + " with current " + currentToken + ":" + scanner.getLexeme());
        if( currentToken == expectedToken) {
            boolean scanResult = scanner.nextToken();
            if( scanResult) {
                currentToken = scanner.getToken();
            }
            else {
                System.out.println("No Token Available");
                String lexeme = scanner.getLexeme();
                if( lexeme == null) {
                    System.out.println("End of file");
                }
                else {
                    System.out.println("Scanner barfed on " + lexeme);
                }
            }
            //System.out.println("   next token is now " + currentToken);
            //System.out.println("   next attri is now " + scanner.getAttribute());
        }
        else {
        	//System.out.println("non-matching token!");
            error();  // We don't match!
        }
    }
    
    /**
     * Handles an error.
     * Prints out the existence of an error and then exits.
     */
    public void error() {
        System.out.println("Error in source code");
        System.exit( 1);
    }
    
    
}
