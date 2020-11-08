package AST;

import AST.Environment.Environment;

public class ASTNeg implements ASTNode {

	int val;
	
	@Override
	public int eval(Environment env) {
		return -val;
	}
	
	public ASTNeg(int n) {
		val = n;
	}

}
