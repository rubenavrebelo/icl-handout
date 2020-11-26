package ast;

import compiler.CodeBlock;
import environment.Environment;

public class ASTSub implements ASTNode {

	ASTNode lhs, rhs;

	public ASTSub(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
	
	@Override
	public int eval(Environment env) throws WrongValueException {
		int v1 = lhs.eval(env);
		int v2 = rhs.eval(env);
		
		return v1 - v2;
	}
	
	public void compile(CodeBlock c, Environment e) {
		lhs.compile(c, e);
		rhs.compile(c, e);
		c.emit("isub");
	}

}