package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTNeg implements ASTNode {

	ASTNode exp;
	
	public ASTNeg(ASTNode exp) {
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException{
		IValue expVal = exp.eval(env);
		if(expVal instanceof VInt) {
			return new VInt(-((VInt)exp).getVal());
		}
		
		throw new TypeErrorException("neg: argument is	not	an integer");
	}
	
	public void compile(CodeBlock c, Environment e) {
		exp.compile(c, e);
		c.emit("ineg");
	}

}
