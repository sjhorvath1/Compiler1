package SyntaxTree;

import static org.junit.Assert.*;

import java.io.File;

import parser.PascalParser;

import org.junit.Test;

import simplescan.TokenType;

public class SyntaxTreeNodeTest {

//	@Test
//	public void test() {
//		ProgramNode treeHead = new ProgramNode("Sample");
//		SubProgramDeclarationsNode mySpace = new SubProgramDeclarationsNode();
//		DeclarationsNode decNode = new DeclarationsNode();
//		decNode.add(new VariableNode("dollars"));
//		decNode.add(new VariableNode("yen"));
//		decNode.add(new VariableNode("bitcoins"));
//		treeHead.setVariables(decNode);
//		treeHead.setFunctions(mySpace);
//		CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
//		AssignmentStatementNode assignNode = new AssignmentStatementNode();
//		assignNode.setlValue(new VariableNode("dollars"));
//		assignNode.setExpression(new ValueNode("1000000"));
//		compoundStatementNode.addStatement(assignNode);
//		treeHead.setMain(compoundStatementNode);
//		AssignmentStatementNode assignmentNode1 = new AssignmentStatementNode();
//		assignmentNode1.setlValue(new VariableNode("yen"));
//		OperationNode opNode = new OperationNode();
//		opNode.setOperation(TokenType.MULTIPLY);
//		opNode.setLeft(new VariableNode("dollars"));
//		opNode.setRight(new ValueNode("102"));
//		assignmentNode1.setExpression(opNode);
//		compoundStatementNode.addStatement(assignmentNode1);
//		AssignmentStatementNode assignmentNode2 = new AssignmentStatementNode();
//		assignmentNode2.setlValue(new VariableNode("bitcoins"));
//		OperationNode opNode2 = new OperationNode();
//		opNode2.setOperation(TokenType.DIVIDE);
//		opNode2.setLeft(new VariableNode("dollars"));
//		opNode2.setRight(new ValueNode("400"));
//		assignmentNode2.setExpression(opNode2);
//		compoundStatementNode.addStatement(assignmentNode2);
//		System.out.println(treeHead.indentedToString(0));
//	}
//	
	@Test
	
	public void Test2(){
		PascalParser newParser = new PascalParser("happypath3.pas");
		
		System.out.println(newParser.program().indentedToString(0));
		
	}

}
