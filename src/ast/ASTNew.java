package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.*;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;
import ivalues.VMCell;

public class ASTNew implements ASTNode {

	ASTNode rhs;
	IType type;

	public ASTNew(ASTNode r) {
		rhs = r;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue v = rhs.eval(env);

		if(v instanceof VInt || v instanceof VBool)
			return new VMCell(v);

		throw new TypeErrorException("new: assignment is neither an Integer nor a Boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {

		if (type instanceof TInt || type instanceof TRef) {
			code.emit("new ref_int");
			code.emit("dup");
			code.emit("invokespecial ref_int/<init>()V");
			code.emit("dup");
			rhs.compile(code, env);
			code.emit("putfield ref_int/v I");
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType v = rhs.typecheck(env);

		if(v instanceof TInt || v instanceof TBool) {
			type = new TRef(v);
			return type;
		}

		throw new TypeErrorException("new: assignment is neither an Integer nor a Boolean");
	}

}
