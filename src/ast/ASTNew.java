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
	
	public ASTNew(ASTNode r) {
		rhs = r;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue v = rhs.eval(env);
		
		if(v instanceof VInt || v instanceof VBool)
			return new VMCell(v);
		
		throw new TypeErrorException("new: assignment is neither int nor bool");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType v = rhs.typecheck(env);
		
		if(v instanceof TInt || v instanceof TBool)
			return new TRef(v);
		
		throw new TypeErrorException("new: assignment is neither int nor bool");
	}

}
