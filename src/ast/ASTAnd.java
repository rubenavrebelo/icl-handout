package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
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
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
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
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType v1 = lhs.typecheck(env);
		if(v1 instanceof VBool) {
			IType v2 = rhs.typecheck(env);
			if(v2 instanceof VBool) {
				return new TBool();
			}
		}
		
		throw new TypeErrorException("&: argument is not a boolean");
	}

}
