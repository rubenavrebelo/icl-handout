package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import ivalues.IValue;
import ivalues.TypeErrorException;

public class ASTSeq implements ASTNode {

	private ASTNode lhs;
	private ASTNode rhs;
	
	public ASTSeq(ASTNode left, ASTNode right) {
		this.lhs = left;
		this.rhs = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		@SuppressWarnings("unused")
		IValue l_eval = lhs.eval(env);
		IValue r_eval = rhs.eval(env);
		
		return r_eval;
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		@SuppressWarnings("unused")
		IType l_eval = lhs.typecheck(env);
		IType r_eval = rhs.typecheck(env);
		
		return r_eval;
	}

}
