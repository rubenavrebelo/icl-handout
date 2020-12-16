package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;

public class ASTIneq implements ASTNode {
	
	ASTNode lhs, rhs;
	String type;
	
	public ASTIneq(ASTNode l, ASTNode r, String op) {
		lhs = l;
		rhs = r;
		type = op;
	}

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
