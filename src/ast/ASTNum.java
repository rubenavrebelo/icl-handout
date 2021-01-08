package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TInt;
import ivalues.IValue;
import ivalues.VInt;


public class ASTNum implements ASTNode {

	int val;
	
	public ASTNum(int n) {
		val = n;
	}

    public IValue eval(Environment<IValue> env) {
    	return new VInt(val);
    }

    public void compile(CodeBlock c, Environment<IValue> env) {
		c.emit("sipush " + this.val);
	}
    
    public IType typecheck (Environment<IType> tenv) {
    	return new TInt();
    }

}

