package SyntaxTree;

import java.util.ArrayList;

/**
 * Creates declaration nodes 
 * @author SJHorvath
 *
 */
public class DeclarationsNode extends SyntaxTreeNode {
	ArrayList<VariableNode> vars;
	
	public DeclarationsNode(){
		vars = new ArrayList<VariableNode>();
	}
	public String indentedToString(int level) {
		String returnString = super.indentedToString(level);
		returnString += "Declarations:\n";
		for(VariableNode a : vars){
			 returnString += a.indentedToString(level+1);
		}
		return returnString;
	}
	
	
	public ArrayList<VariableNode> getDeclarations(){
		return vars;
	}
	public void add(VariableNode var){
		vars.add(var);
	}
	
	

}
