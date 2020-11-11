package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id)
    {
		this.id = id;
    }
	 
	public int eval(Environment env)
    {
		Object obj = null;
		obj = env.find(id);
		return (Integer) obj; 
	}
	
	public void compile(CodeBlock c, Environment e) {
		c.emit("aload 3");
	}

}