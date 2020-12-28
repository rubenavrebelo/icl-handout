package ast;

import compiler.CodeBlock;
import environment.Environment;
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
	public IValue eval(Environment env) throws TypeErrorException {
		IValue vCond = c.eval(env);
		IValue vExp = exp.eval(env);
		
		if(vCond instanceof VBool) {
			while(((VBool)vCond).getVal()) {
				vCond = c.eval(env);
				vExp = exp.eval(env);
				if(!(vCond instanceof VBool))
					throw new TypeErrorException("");
			}
			return vCond;
		}
		throw new TypeErrorException("");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
