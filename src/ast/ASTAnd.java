package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTAnd implements ASTNode {

	ASTNode lhs, rhs;
	
	public ASTAnd(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VBool) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VBool) {
				return ((VBool)v1).and((VBool) v2);
			}
		}
		
		throw new TypeErrorException("&: argument is not a boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}