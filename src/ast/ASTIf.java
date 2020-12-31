package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;

public class ASTIf implements ASTNode {
	
	ASTNode c, t, f;
	
	public ASTIf(ASTNode c, ASTNode t, ASTNode f)
    {
			this.c = c; this.t = t; this.f = f;
    }

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue vC = c.eval(env);
		
		if(vC instanceof VBool) {
			IValue vT = t.eval(env);
			IValue vF = f.eval(env);
			if(vT instanceof VBool && vF instanceof VBool) {
				if(((VBool) vC).getVal())
					return vT;
				return vF;
			}
			if(vT instanceof VInt && vF instanceof VInt) {
				if(((VBool) vC).getVal())
					return vT;
				return vF;
			}
		}
		throw new TypeErrorException("if: arguments are not valid");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		// TODO Auto-generated method stub

	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType tC = c.typecheck(env);
		
		if(tC instanceof TBool) {
			return new TBool();
		}
		throw new TypeErrorException("if: arguments are not valid");
		}
}
