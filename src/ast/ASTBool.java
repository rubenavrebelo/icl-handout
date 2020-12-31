package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTBool implements ASTNode {
	
	private boolean v;
	
	public ASTBool(boolean bool) {
		this.v = bool;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		return new VBool(v);
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		return new TBool();
	}

}
