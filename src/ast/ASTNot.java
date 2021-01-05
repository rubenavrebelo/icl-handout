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
		exp.compile(code, env);
		// If it is a 0, go to L1 and turn it into a 1
		// if it is not a 0, push into the stack a zero and end
		code.emit("ifeq L1");
		code.emit("sipush 0");
		code.emit("goto L2");
		code.emit("L1:");
		code.emit("sipush 1");
		code.emit("L2:");
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
