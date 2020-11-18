package ast;

import compiler.CodeBlock;
import environment.Environment;

public class ASTNum implements ASTNode {

	int val;
	
	public ASTNum(int n) {
		val = n;
	}

    public int eval(Environment env) {
    	return val;
    }

    public void compile(CodeBlock c, Environment env) {
		c.emit("sipush " + this.val);
	}

}

