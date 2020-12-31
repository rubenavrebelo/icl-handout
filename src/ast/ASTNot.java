package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
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
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue expVal = exp.eval(env);
		
		if(expVal instanceof VBool) {
			return new VBool(!((VBool)expVal).getVal());
		}
		throw new TypeErrorException("~: argument is not boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType expVal = exp.typecheck(env);
		
		if(expVal instanceof TBool) {
			return new TBool();
		}
		throw new TypeErrorException("~: argument is not boolean");
	}

}
