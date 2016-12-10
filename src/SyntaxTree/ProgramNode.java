package SyntaxTree;

/**
 * Creates the program node
 * @author SJHorvath
 *
 */
public class ProgramNode extends SyntaxTreeNode {
	String name;
	DeclarationsNode variables;
	CompoundStatementNode main;
	SubProgramDeclarationsNode functions;
	
	public String indentedToString(int level){
		String returnString = super.indentedToString(level);
		returnString += "Program: "+name+"\n";
		returnString += variables.indentedToString(level+1);
		if (functions != null) {returnString += functions.indentedToString(level+1);}
		if (main != null) {returnString += main.indentedToString(level+1);}
	
		return returnString;
		
	}
	
	public ProgramNode(){
		
	}
	public ProgramNode(String nameString){
		name = nameString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeclarationsNode getVariables() {
		return variables;
	}

	public void setVariables(DeclarationsNode variables) {
		this.variables = variables;
	}

	public CompoundStatementNode getMain() {
		return main;
	}

	public void setMain(CompoundStatementNode main) {
		this.main = main;
	}

	public SubProgramDeclarationsNode getFunctions() {
		return functions;
	}

	public void setFunctions(SubProgramDeclarationsNode functions) {
		this.functions = functions;
	}
}
