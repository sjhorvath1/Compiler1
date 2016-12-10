package SyntaxTree;

/**
 * Creates variable nodes
 * @author SJHorvath
 *
 */
public class VariableNode extends ExpressionNode {
	String attribute;
	
	public String indentedToString( int level) {
        String answer = super.indentedToString(level);
        answer += "Value: " + this.attribute + "\n";
        return answer;
    }
	
	public VariableNode(String inputName){
		attribute = inputName;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	

}
