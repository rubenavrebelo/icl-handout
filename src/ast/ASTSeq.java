package ast;

import compiler.CodeBlock;
import environment.Environment;
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
	public IValue eval(Environment env) throws TypeErrorException {
		@SuppressWarnings("unused")
		IValue l_eval = lhs.eval(env);
		IValue r_eval = rhs.eval(env);
		
		return r_eval;
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
