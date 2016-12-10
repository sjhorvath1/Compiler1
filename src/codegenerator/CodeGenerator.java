package codegenerator;

import java.util.ArrayList;
import simplescan.*;
import symbolTable.*;
import SyntaxTree.*;

public class CodeGenerator {
	ProgramNode treeNode;
	private int currentTRegister;
	private int countingThing;
	
	
	public CodeGenerator(ProgramNode treeNode){
		this.treeNode = treeNode;
		this.currentTRegister = 0;
	}
	
	
	public String stringGen(){
		String code = "";
		code += ".data\n";
		code += writeDeclarations(treeNode);
		code += "\n\n.text\nmain:\n";
		for(StatementNode sn: treeNode.getMain().getStatements()){
			code += writeCode(sn);
		}
		code += "addi\t$v0,     $zero,    10\n";
		code += "syscall\n";
		return code;
	}
	
	private String writeDeclarations(ProgramNode pn){
		String answer= " ";
		if(!pn.getFunctions().getProcs().isEmpty()){
			for(ProgramNode a: pn.getFunctions().getProcs()){
				answer+=writeDeclarations(a);
			}
		}
		DeclarationsNode dn = pn.getVariables();
		ArrayList<VariableNode> decList = dn.getDeclarations();
		for(VariableNode e: decList){
			answer += e.getAttribute()+":\t.word\t0\n";
					
		}
		return answer;
	}
	public String writeCode(StatementNode stateNode){
		String code = "";
		if(stateNode instanceof AssignmentStatementNode){
			code += writeCode((AssignmentStatementNode)stateNode);
		}
//		if(node instanceof ProcedureCallNode){
//			code += writeCode((ProcedureCallNode)stateNode){
//				
//			}
//		}
		if(stateNode instanceof CompoundStatementNode){
			for(StatementNode sn : ((CompoundStatementNode) stateNode).getStatements()){
				code += writeCode(sn);
			}
		}
		if(stateNode instanceof IfStatementNode){
			code += writeCode((IfStatementNode)stateNode);
		}
		
		if(stateNode instanceof WhileStatementNode){
			code += writeCode((WhileStatementNode)stateNode);
		}
		
		return code;
	}
	
	public String writeCode(ExpressionNode expressNode, String resultReg){
		String code = "";
		if(expressNode instanceof OperationNode){
			code += writeCode((OperationNode)expressNode, resultReg);
		}

		else if(expressNode instanceof ValueNode){
			code += writeCode((ValueNode)expressNode, resultReg);
		}
		else if(expressNode instanceof VariableNode){
			code += writeCode((VariableNode)expressNode, resultReg);
		}
		return code;
	}
	
	
	
	public String writeCode(VariableNode node, String resultReg){
		String code = " ";
		code += "lw\t"+resultReg + ",    "+node.getAttribute() + "\n";
		return code;
	}
	
	public String writeCode(IfStatementNode ifNode){
		String code = "#if statement\n";
		String elseLabel = "ifelse" + countingThing;
		String endIfLabel = "endif" + countingThing++;
		ExpressionNode condition = ifNode.getCondition();
		StatementNode thenBody = ifNode.getThenStatement();
		StatementNode elseBody = ifNode.getElseStatement();
		String conditionReg = "$t" + currentTRegister++;
		code += writeCode(condition,conditionReg);
		code +="beq\t" + conditionReg + ",    $0,    "+elseLabel + "\n";
		code += writeCode(thenBody);
		code += "j\t"+endIfLabel + "\n";
		code += elseLabel +":\n";
		code += writeCode(elseBody);
		code += endIfLabel + ":\n";
		return code;
		
	}
	
	public String writeCode(WhileStatementNode whileNode){
		String code = "#while statement\n";
		String whileStart = "whilestart"+countingThing;
		String whileEnd = "whileend" + countingThing;
		ExpressionNode whilecondition = whileNode.getCondition();
		StatementNode whilebody = whileNode.getBody();
		String conditionreg = "$t" + currentTRegister++;
		code += whileStart + ":\n";
		code += writeCode(whilecondition, conditionreg);
		code += "beq\t" + conditionreg + ",   $0,   "+ whileEnd + "\n";
		code += writeCode(whilebody);
		code += "j\t" + whileStart + "\n";
		code += whileEnd + ":\n";
		return code;
		
	}
	
	public String writeCode(AssignmentStatementNode assignNode){
		String code = "";
		String assignTo = assignNode.getlValue().getAttribute();
		String resultReg = "$t" + currentTRegister++;
		code += writeCode(assignNode.getExpression(),resultReg);
		code += "sw\t"+ resultReg + ",    " + assignTo +"\n";
		return code;
	}
	
	public String writeCode(OperationNode opNode, String resultReg){
		String code = "";
		ExpressionNode left = opNode.getLeft();
		String leftReg = "$t" + currentTRegister++;
		code += writeCode(left, leftReg);
		ExpressionNode right = opNode.getRight();
		String rightReg = "$t" + currentTRegister++;
		code += writeCode(right, rightReg);
		TokenType op = opNode.getOperation();
		
		if(op == TokenType.PLUS){
			code += "add\t" + resultReg + ",    " +leftReg+ ",     " + rightReg + "\n";
		}
		
		if( op == TokenType.MINUS){
			code += "sub\t"+resultReg+ ",    " + leftReg + ",     " + rightReg + "\n";
					
		}
		
		if(op == TokenType.MULTIPLY){
			code += "mult\t" + leftReg +",  "+rightReg+"\n";
			code += "mflo\t" + resultReg + "\n";
		}
		
		if(op == TokenType.DIVIDE){
			code += "div\t" + leftReg + ",    " + rightReg + "\n";
			code += "mflo\t"+ resultReg + "\n";
		}
		
		if(op == TokenType.MOD){
			code += "div\t"+leftReg+",   "+rightReg + "\n";
			code += "mfhi\t"+ resultReg + "\n";
		}
		if(op == TokenType.AND){
			code += "#and relop\n";
			String andFalseLabel = "andfalse" + countingThing + "\n";
			String andEndLabel = "andEnd" + countingThing++ + "\n";
			code += "beq\t" + leftReg +",   $0,   " + andFalseLabel;
			code += "beq\t" + rightReg + ",  $0,   "+ andFalseLabel;
			code += "addi\t" + resultReg+",   $zero,   1\n";
			code += "j\t" + andEndLabel;
			code += andFalseLabel + ":\n";
			code += "addi\t"+resultReg + ",   $zero, 0\n";
			code += andEndLabel+ ":\n";
		}
		if(op == TokenType.EQUALS){
			code += "#equality test\n";
			String equalsLabel = "equalstrue" + countingThing;
			String equalsTestEnd = "equalsend" + countingThing++;
			code += "sub\t"+resultReg+",   "+leftReg + ",   "+rightReg + "\n";
			code += "beq\t$zero,   "+resultReg +",    "+equalsLabel + "\n";
			code += "addi\t"+resultReg+",   $zero, 0\n";
			code += "j\t" + equalsTestEnd + "\n";
			code += equalsLabel + ":\n";
			code += "addi\t" + resultReg + ",   $zero, 1\n";
			code += equalsTestEnd+ ":\n";
		}
		
		if (op == TokenType.NOT_EQUAL){
			//this is the equals code copied and pasted
			//with the results reversed
			code += "#inequality test\n";
			String equalslabel = "notequalsfalse" + countingThing;
			String equalstestend = "notequalsend" + countingThing++;
			code += "sub\t" + resultReg + ",   " + leftReg + ",   " + rightReg + "\n";
			code += "beq\t$zero,    " + resultReg + ",   " + equalslabel +"\n";
			code += "addi\t" + resultReg + ",   $zero,   1\n";
			code += equalslabel +":\n";
			code += "addi\t" + resultReg + ",   $zero,   0\n";
			code += "j\t" + equalstestend +"\n";
			code += equalstestend+":\n";
		}
		if(op == TokenType.LESS_THAN){
			code += "slt\t" + resultReg + ",   " + leftReg + ",   " + rightReg;
		}
		if(op == TokenType.LESS_THAN_EQUALS){
			String lessequalsfalselabel = "lessequalsfalselabel" + countingThing;
			String lessequalsendlabel = "lessequalsendlabel" + countingThing++;
			code += "sub\t" + resultReg + ",   " + leftReg + ",   " + rightReg;
			code += "bgtz\t" + resultReg + ",   " + lessequalsfalselabel;
			code += "addi\t" + resultReg + ",   $zero,   " + 1;
			code += "j\t"+ lessequalsendlabel;
			code += lessequalsfalselabel + ":";
			code += "addi\t" + resultReg + ",   $zero,   " + 0;
			code += lessequalsendlabel + ":";
		}
		if( op == TokenType.GREATER_THAN)
		{
			//less than code reversed
			code += "slt\t" + resultReg + ",   " + rightReg + ",   " + leftReg;
		}
		if(op == TokenType.GREATER_THAN_EQUALS){
			String greaterequalsfalselabel = "greaterequalsfalselabel" + countingThing;
			String greaterequalsendlabel = "greaterequalsendlabel" + countingThing++;
			code += "sub\t" + resultReg + ",   " + rightReg + ",   " + leftReg;
			code += "bgtz\t" + resultReg + ",   " + greaterequalsfalselabel;
			code += "addi\t" + resultReg + ",   $zero,   " + 1;
			code += "j\t"+ greaterequalsendlabel;
			code += greaterequalsfalselabel + ":";
			code += "addi\t" + resultReg + ",   $zero,   " + 0;
			code += greaterequalsendlabel + ":";
		}
		
		return code;
	}
	
	public String writeCode(ValueNode node, String resultRegister){
		String value = node.getName();
		String code = "addi\t"+resultRegister +",   $zero,"+value +"\n";
		return code;
	}
	
	
	
	
}
