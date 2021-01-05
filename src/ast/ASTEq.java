package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
import itypes.TInt;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTEq implements ASTNode {

	ASTNode lhs, rhs;

	public ASTEq(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VInt) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VInt) {
				return ((VInt)v1).eq((VInt)v2);

			}
			throw new TypeErrorException("==: arguments is not integer");
		}
		throw new TypeErrorException("==: arguments is not integer");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		lhs.compile(code, env);
		rhs.compile(code, env);
		
		code.emit("isub");
		code.emit("ifeq L1");
		code.emit("sipush 0");
		code.emit("goto L2");
		code.emit("L1: sipush 1");
		code.emit("L2:");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType t1 = lhs.typecheck(env);
		IType t2 = rhs.typecheck(env);
		if (t1 instanceof TInt && t2 instanceof TInt)
			return new TBool();
		throw new TypeErrorException("==: argument is not an Integer");
	}

}
