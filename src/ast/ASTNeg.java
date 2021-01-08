package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TInt;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTNeg implements ASTNode {

	ASTNode exp;

	public ASTNeg(ASTNode exp) {
		this.exp = exp;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException{
		IValue expVal = exp.eval(env);
		if(expVal instanceof VInt) {
			return new VInt(-((VInt)exp).getVal());
		}

		throw new TypeErrorException("neg: argument is	not	an integer");
	}

	public void compile(CodeBlock c, Environment<IValue> e) {
		exp.compile(c, e);
		c.emit("ineg");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType t1 = exp.typecheck(env);	
		if	(t1	instanceof TInt)
			return new TInt();	
		throw new TypeErrorException("-:argument is	not	an integer");
	}

}
