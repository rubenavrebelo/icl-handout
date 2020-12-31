package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;

public class ASTWhile implements ASTNode {
	ASTNode c, exp;

	public ASTWhile(ASTNode cond, ASTNode exp) {
		c = cond;
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue vCond = c.eval(env);
		@SuppressWarnings("unused")
		IValue vExp = exp.eval(env);
		
		if(vCond instanceof VBool) {
			while(((VBool)vCond).getVal()) {
					vExp = exp.eval(env);
					vCond = c.eval(env);
				if(!(vCond instanceof VBool))
					throw new TypeErrorException("");
			}
			return vCond;
		}
		throw new TypeErrorException("");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType tCond = c.typecheck(env);
		
		if(tCond instanceof VBool) {
			@SuppressWarnings("unused")
			IType vExp = exp.typecheck(env);
			return tCond;
		}
		throw new TypeErrorException("");
	}

}
