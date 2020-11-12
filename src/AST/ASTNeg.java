package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTNeg implements ASTNode {

	ASTNode exp;
	
	@Override
	public int eval(Environment env) {
		int expVal = (int) exp.eval(env);
		return -expVal;
	}
	
	public ASTNeg(ASTNode exp) {
		this.exp = exp;
	}
	
	public void compile(CodeBlock c, Environment e) {
		ASTNode zero = new ASTNum(0);
		ASTNode neg = new ASTSub(zero, exp);
		neg.compile(c, e);
	}

}
