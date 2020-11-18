package ast;

import compiler.CodeBlock;
import environment.Environment;

public class ASTNeg implements ASTNode {

	ASTNode exp;
	
	public ASTNeg(ASTNode exp) {
		this.exp = exp;
	}
	
	@Override
	public int eval(Environment env) throws WrongValueException{
		int expVal = (int)exp.eval(env);
		return -expVal;
	}
	
	public void compile(CodeBlock c, Environment e) {
//		ASTNode zero = new ASTNum(0);
//		ASTNode neg = new ASTSub(zero, exp);
//		neg.compile(c, e);
		exp.compile(c, e);
		c.emit("ineg\r\n");
	}

}
