package AST;

import AST.Environment.Environment;

public class ASTDef implements ASTNode {

	String id;
	ASTNode init;
	ASTNode body;
	
	public ASTDef(String id, ASTNode init, ASTNode body) {
		this.id = id;
		this.init = init;
		this.body = body;
	}

	@Override
	public int eval(Environment env) {
		int val = init.eval(env);
		Environment env2 = env.beginScope();
		env2.assoc(id, val);
		val = body.eval(env2);
		env2.endScope();
		return val;
	}
	
	
}
