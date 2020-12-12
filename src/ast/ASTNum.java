package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.VInt;


public class ASTNum implements ASTNode {

	int val;
	
	public ASTNum(int n) {
		val = n;
	}

    public IValue eval(Environment env) {
    	return new VInt(val);
    }

    public void compile(CodeBlock c, Environment env) {
		c.emit("sipush " + this.val);
	}

}

