package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TInt;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VInt;

public class ASTPlus implements ASTNode {

	ASTNode lhs, rhs;
	
	public ASTPlus(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}

	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue v1 = lhs.eval(env);
		if(v1 instanceof VInt) {
			IValue v2 = rhs.eval(env);
			if(v2 instanceof VInt) {
				return ((VInt)v1).add((VInt)v2);
			}
		}
		
		throw new TypeErrorException("+:argument is	not	an integer");
	}
	
    public void compile(CodeBlock c, Environment<IValue> e) {
		lhs.compile(c, e);
		rhs.compile(c, e);
		c.emit("iadd");
	}

    public IType typecheck(Environment<IType> tenv) throws TypeErrorException	{	
    	IType t1 = lhs.typecheck(tenv);	
    	if	(t1	instanceof TInt) {
    		IType v2 = rhs.typecheck(tenv);
    		if (v2 instanceof TInt)
    			return new TInt();	
    		throw new TypeErrorException("+:argument is	not	an integer");
    	}
		throw new TypeErrorException("+:argument is	not	an integer");
    }
}

