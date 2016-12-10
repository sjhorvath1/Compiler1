package SyntaxTree;
/**
 * Responsible for the creation of If Statement Nodes
 * @author SJHorvath
 *
 */
public class IfStatementNode extends StatementNode {
	ExpressionNode condition;
	StatementNode thenStatement;
	StatementNode elseStatement;
	
	public String indentedToString(int level){
		String answer = super.indentedToString(level);
		answer += "If Statement\n"
				+super.indentedToString(level) + "Condition:\n";
		answer +=  condition.indentedToString(level+1);
		answer += super.indentedToString(level) + "Body:\n";
		answer += thenStatement.indentedToString(level+1);
		answer += super.indentedToString(level) + "Else:\n";
		answer += elseStatement.indentedToString(level+1);
		return answer;
	}

	public ExpressionNode getCondition() {
		return condition;
	}

	public void setCondition(ExpressionNode condition) {
		this.condition = condition;
	}

	public StatementNode getThenStatement() {
		return thenStatement;
	}

	public void setThenStatement(StatementNode thenStatement) {
		this.thenStatement = thenStatement;
	}

	public StatementNode getElseStatement() {
		return elseStatement;
	}

	public void setElseStatement(StatementNode elseStatement) {
		this.elseStatement = elseStatement;
	}
}
