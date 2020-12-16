package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTSeq implements ASTNode {

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		// TODO Auto-generated method stub
		return new VInt(0);
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
