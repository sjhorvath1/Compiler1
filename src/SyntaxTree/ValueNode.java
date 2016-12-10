package SyntaxTree;

/**
 * Creates value nodes
 * @author SJHorvath
 *
 */
public class ValueNode extends ExpressionNode {
	String name;
	
	public ValueNode(String inString){
		name = inString;
	}
	
	public String indentedToString( int level) {
        String answer = super.indentedToString(level);
        answer += "Value: " + this.name + "\n";
        return answer;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
