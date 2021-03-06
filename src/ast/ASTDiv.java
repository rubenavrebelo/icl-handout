package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TInt;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTDiv implements ASTNode {
	
	ASTNode lhs, rhs;
	
	public ASTDiv(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }
	
	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VInt) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VInt) {
				return ((VInt)v1).div((VInt)v2);
			}
		}
		
		throw new TypeErrorException("/:argument is	not	an integer");
	}
	
	public void compile(CodeBlock c, Environment<IValue> e) {
		lhs.compile(c, e);
		rhs.compile(c, e);
		c.emit("idiv");
	}

	@Override
	public IType typecheck(Environment<IType> tenv) throws TypeErrorException {
		IType t1 = lhs.typecheck(tenv);	
    	if	(t1	instanceof TInt) {
    		IType v2 = rhs.typecheck(tenv);
    		if (v2 instanceof TInt)
    			return new TInt();	
    		throw new TypeErrorException("/:argument is	not	an integer");
    	}
		throw new TypeErrorException("/:argument is	not	an integer");
	}

}