package SyntaxTree;

import simplescan.TokenType;
/**
 * Creates operation nodes
 * @author SJHorvath
 *
 */
public class OperationNode extends ExpressionNode{
	TokenType operation;
	ExpressionNode left;
	ExpressionNode right;
	
	public OperationNode(){
		
	}
	public OperationNode(TokenType op){
		operation = op;
	}
	
	public TokenType getOperation() {
		return operation;
	}

	public void setOperation(TokenType operation) {
		this.operation = operation;
	}

	public ExpressionNode getLeft() {
		return left;
	}

	public void setLeft(ExpressionNode left) {
		this.left = left;
	}

	public ExpressionNode getRight() {
		return right;
	}

	public void setRight(ExpressionNode right) {
		this.right = right;
	}

	public String indentedToString( int level) {
        String answer = super.indentedToString(level);
        answer += "Operation: " + this.operation + "\n";
        answer += left.indentedToString(level + 1);
        answer += right.indentedToString(level + 1);
        return( answer);
    }
}
