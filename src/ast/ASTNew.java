package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;
import ivalues.VMCell;

public class ASTNew implements ASTNode {
	
	ASTNode rhs;

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue v = rhs.eval(env);
		
		if(v instanceof VInt || v instanceof VBool)
			return new VMCell(v);
		
		throw new TypeErrorException("new: assignment is neither int nor bool");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
