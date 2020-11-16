package AST;

import java.util.Map;

import AST.Environment.Environment;
import compiler.CodeBlock;

public class ASTDef implements ASTNode {

	Map<String, ASTNode> ids;
	ASTNode body;
	
	public ASTDef(Map<String, ASTNode> ids, ASTNode body) {
		this.ids = ids;
		this.body = body;
	}

	@Override
	public int eval(Environment env) {
		
		Environment env2 = env.beginScope();
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			int val1 = id.getValue().eval(env2);
			env2.assoc(id.getKey(), val1);
		}
		
		int val2 = body.eval(env2);
		env2 = env2.endScope();
		return val2;
	}
	
	public void compile(CodeBlock c, Environment e) {
		Environment newEnv = e.beginScope();
//		c.emit("new " + newEnv);
//		c.emit("dup");
//		c.emit("invokespecial " + newEnv + "/<init>()V");
//		c.emit("dup");
//		c.emit("aload 3");
//		c.emit("putfield " + newEnv +"/3 " + "LAST/Environment/Environment");
		c.emit("astore 3");
	}
	
	
}
