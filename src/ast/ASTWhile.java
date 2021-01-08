package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTWhile implements ASTNode {
	ASTNode cond, exp;

	public ASTWhile(ASTNode cond, ASTNode exp) {
		this.cond = cond;
		this.exp = exp;
	}
	
	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue vCond = cond.eval(env);
		@SuppressWarnings("unused")
		IValue vExp = exp.eval(env);
		
		if(vCond instanceof VBool) {
			while(((VBool)vCond).getVal()) {
					vExp = exp.eval(env);
					vCond = cond.eval(env);
				if(!(vCond instanceof VBool))
					throw new TypeErrorException("while: argument is not a Boolean");
			}
			return vCond;
		}
		throw new TypeErrorException("while: argument is not a Boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		int nextLabel1 = code.getNewLabel();
		int nextLabel2 = code.getNewLabel();
		
		code.emit("L" + nextLabel1 + ":");
		cond.compile(code, env);
		code.emit("ifeq L" + nextLabel2);
		exp.compile(code, env);
		code.emit("goto L" + nextLabel1);
		code.emit("L" + nextLabel2 + ":");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType tCond = cond.typecheck(env);
		
		if(tCond instanceof VBool) {
			@SuppressWarnings("unused")
			IType vExp = exp.typecheck(env);
			return tCond;
		}
		throw new TypeErrorException("while: argument is not a Boolean");
	}

}
