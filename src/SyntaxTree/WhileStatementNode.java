package SyntaxTree;

/**
 * Creates while statement nodes
 * @author SJHorvath
 *
 */
public class WhileStatementNode extends StatementNode {
		ExpressionNode condition;
		StatementNode body;
		
		
		public String indentedToString(int level){
			String answer = super.indentedToString(level);
			answer += "While Statement\n"
					+super.indentedToString(level) + "Condition:\n";
			answer +=  condition.indentedToString(level+1);
			answer += super.indentedToString(level) + "Body:\n";
			answer += body.indentedToString(level+1);
		
			return answer;
		}

		public ExpressionNode getCondition() {
			return condition;
		}

		public void setCondition(ExpressionNode condition) {
			this.condition = condition;
		}

		public StatementNode getBody() {
			return body;
		}

		public void setBody(StatementNode body) {
			this.body = body;
		}

	
	}

