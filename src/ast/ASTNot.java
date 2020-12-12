package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTNot implements ASTNode {

	ASTNode exp;
	
	public ASTNot(ASTNode exp)
    {
			this.exp = exp;
    }
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue expVal = exp.eval(env);
		
		if(expVal instanceof VBool) {
			return new VBool(!((VBool)expVal).getVal());
		}
		throw new TypeErrorException("~: argument is not valid");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
