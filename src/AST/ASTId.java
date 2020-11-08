package AST;

import AST.Environment.Environment;

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

}