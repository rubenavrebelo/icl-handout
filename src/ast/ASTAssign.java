package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;
import ivalues.VMCell;

public class ASTAssign implements ASTNode {
	
	ASTNode lhs, rhs;
	
	public ASTAssign(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue	v1 = lhs.eval(env);	
		if	(v1	instanceof VMCell)	{	
			IValue v2 =	rhs.eval(env);
			((VMCell)v1).set(v2);	
			return	v2;	
		}	
		throw new TypeErrorException("assignment := :lhs is not a	reference");	
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
