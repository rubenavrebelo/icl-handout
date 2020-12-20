package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTOr implements ASTNode {

	ASTNode lhs, rhs;
	
	public ASTOr(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VBool) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VBool) {
				return ((VBool)v1).or((VBool) v2);//TODO:'or' instead of 'and'
			}
		}
		
		throw new TypeErrorException("&: argument is not a boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
