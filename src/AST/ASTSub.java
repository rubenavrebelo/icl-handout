package AST;

import AST.Environment.Environment;

public class ASTSub implements ASTNode {

	ASTNode lhs, rhs;

	@Override
	public int eval(Environment env) {
		int v1 = lhs.eval(env);
		int v2 = rhs.eval(env);
		return v1 - v2;
	}
	
	public ASTSub(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}

}
