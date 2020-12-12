package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;

public class ASTIf implements ASTNode {
	
	ASTNode c, t, f;
	
	public ASTIf(ASTNode c, ASTNode t, ASTNode f)
    {
			this.c = c; this.t = t; this.f = f;
    }

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue vC = c.eval(env);
		
		if(vC instanceof VBool) {
			IValue vT = t.eval(env);
			if(vT instanceof VBool) {
				IValue vF = f.eval(env);
				if(vF instanceof VBool) {
					if(((VBool) vC).getVal())
						return vT;
					return vF;
				}
			}
		}
		throw new TypeErrorException("if: argument is not boolean");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub

	}

}
