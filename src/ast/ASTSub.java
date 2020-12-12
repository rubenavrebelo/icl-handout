package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTSub implements ASTNode {

	ASTNode lhs, rhs;

	public ASTSub(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VInt) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VInt) {
				return ((VInt)v1).sub((VInt)v2);
			}
		}
		
		throw new TypeErrorException("-:argument is	not	an integer");
	}
	
	public void compile(CodeBlock c, Environment e) {
		lhs.compile(c, e);
		rhs.compile(c, e);
		c.emit("isub");
	}

}
