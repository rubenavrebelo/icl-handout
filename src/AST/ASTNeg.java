package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTNeg implements ASTNode {

	int val;
	
	@Override
	public int eval(Environment env) {
		return -val;
	}
	
	public ASTNeg(int n) {
		val = n;
	}
	
	public void compile(CodeBlock c, Environment e) {

	}

}
