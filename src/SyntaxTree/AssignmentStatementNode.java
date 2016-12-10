package SyntaxTree;

/**
 * Creates assignment statement nodes
 * @author SJHorvath
 *
 */
public class AssignmentStatementNode extends StatementNode {
	VariableNode lValue;
	ExpressionNode expression;
	
	/** Gets the left tree value
	 *@return VariableNode 
	 **/
	public VariableNode getlValue() {
		return lValue;
	}
	/**
	 * Sets the left value
	 * @param lValue
	 */
	public void setlValue(VariableNode lValue) {
		this.lValue = lValue;
	}
	/**
	 * Gets the expression from the node
	 * @return expression node
	 */
	public ExpressionNode getExpression() {
		return expression;
	}
	
	/**
	 * Sets the expression
	 * @param expression
	 */
	public void setExpression(ExpressionNode expression) {
		this.expression = expression;
	}
	
	public String indentedToString(int level){
		String returnString = super.indentedToString(level);
		returnString += "Assignment Statement:\n";
		returnString += lValue.indentedToString(level+1);
		returnString += expression.indentedToString(level+1);
		return returnString;
		
	}
	
	
}
