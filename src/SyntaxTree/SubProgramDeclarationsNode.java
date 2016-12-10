package SyntaxTree;

import java.util.ArrayList;

/**
 * Creates sub program declaration nodes
 * @author SJHorvath
 *
 */
public class SubProgramDeclarationsNode extends SyntaxTreeNode {
	ArrayList<ProgramNode> procs;
	
	public SubProgramDeclarationsNode(){
		procs = new ArrayList<ProgramNode>();
	}
	
	
	public ArrayList<ProgramNode> getProcs() {
		return procs;
	}


	public void setProcs(ArrayList<ProgramNode> procs) {
		this.procs = procs;
	}


	public void add(ProgramNode node){
		procs.add(node);
	}
	public String indentedToString(int level) {
		String returnString = super.indentedToString(level);
		returnString += "SubProgram Declarations:\n";
		for(ProgramNode a : procs){
			returnString += a.indentedToString(level+1);
		}
		return returnString;
	}

}
