package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTNum implements ASTNode {

	int val;

    public int eval(Environment env) { return val; }

    public ASTNum(int n)
    {
	   val = n;
    }

    public void compile(CodeBlock c, Environment e) {
		c.emit("sipush" + this.val);
	}
}

