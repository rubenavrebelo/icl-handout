package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
import itypes.TInt;
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
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
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
	public void compile(CodeBlock code, Environment<IValue> env) {
		lhs.compile(code, env);
		rhs.compile(code, env);

		code.emit("isub");
		switch (type) {
		case "<":
			code.emit("iflt L1");
			break;
		case "<=":
			code.emit("ifle L1");
			break;
		case ">":
			code.emit("ifgt L1");
			break;
		case ">=":
			code.emit("ifge L1");
			break;
		default:
			break;
		}
		
		code.emit("sipush 0");
		code.emit("goto L2");
		code.emit("L1: sipush 1");
		code.emit("L2:");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType v1 = lhs.typecheck(env);
		IType v2 = rhs.typecheck(env);

		switch (type) {
		case "<":
		case "<=":
		case ">":
		case ">=":
			if(v1 instanceof TInt && v2 instanceof TInt)
				return new TBool();
			break;
		}	
		throw new TypeErrorException(type + ": arguments are invalid");
	}

}
