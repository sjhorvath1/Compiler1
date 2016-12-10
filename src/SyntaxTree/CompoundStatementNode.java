package SyntaxTree;

import java.util.ArrayList;

/**
 * Creates Compound Statement Nodes
 * @author SJHorvath
 *
 */
public class CompoundStatementNode extends StatementNode{
	ArrayList<StatementNode> statements;
	
	public CompoundStatementNode(){
		statements = new ArrayList<StatementNode>();
	}
	
	public void addStatement(StatementNode statement){
		statements.add(statement);
	}
	
	
	public ArrayList<StatementNode> getStatements() {
		return statements;
	}

	public String indentedToString(int level) {
		String returnString = super.indentedToString(level);
		returnString += "Compound Statement:\n";
		for(StatementNode a : statements){
			 returnString += a.indentedToString(level+1);
		}
		return returnString;
	}

	
}
