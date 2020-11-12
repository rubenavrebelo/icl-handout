package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTMult implements ASTNode {
	
	ASTNode lhs, rhs;
	
	public ASTMult(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }
	 
	public int eval(Environment env)
    { 
    	int v1 = lhs.eval(env);
		int v2 = rhs.eval(env);
        return v1*v2; 
	}

	public void compile(CodeBlock c, Environment e) {
		lhs.compile(c, e);
		rhs.compile(c, e);
		c.emit("imul");
	}
}
