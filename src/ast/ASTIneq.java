package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

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
		IValue v1 = lhs.eval(env);
		IValue v2 = rhs.eval(env);
		
		if(v1 instanceof VInt && v2 instanceof VInt) {
			VInt int1 = ((VInt)v1);
			VInt int2 = ((VInt)v2);
			switch (type) {
			case "<":
				return int1.lessThan(int2);
			case "<=":
				return int1.lessEqThan(int2);
			case ">":
				return int1.moreThan(int2);
			case ">=":
				return int1.moreEqThan(int2);
			}
		}
		
		throw new TypeErrorException(type + ": arguments are invalid");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
