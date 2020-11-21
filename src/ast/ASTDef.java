package ast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import compiler.CodeBlock;
import compiler.Frame;
import environment.Environment;

public class ASTDef implements ASTNode {

	Map<String, ASTNode> ids;
	Map<String, ASTNode> list;
	ASTNode body;
	
	public ASTDef(Map<String, ASTNode> ids, ASTNode body, Map<String, ASTNode> list) {
		this.ids = ids;
		this.body = body;
		this.list = list;
	}

	@Override
	public int eval(Environment env) throws WrongValueException {
		
		Environment env2 = env.beginScope();
		int val1;
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			val1 = id.getValue().eval(env);
			env2.assoc(id.getKey(), val1);
		}
		
		int val2 = body.eval(env2);
		env2.endScope();
		return val2;
	}
	
	public void compile(CodeBlock c, Environment e) {
		Environment newEnv = e.beginScope();
		List<Integer> parameters = new  LinkedList<>();
		
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			try {
				parameters.add(id.getValue().eval(newEnv));
			} catch (WrongValueException e1) {
				e1.printStackTrace();
			}
		}
		
		Frame newFrame = c.createFrame(parameters);
		String frameName = newFrame.getFrameName();
		Frame ancestorFrame = newFrame.getAncestor();
		
		//TODO
		c.setCurrentFrame(newFrame);
		this.startFrame(c, frameName);
		if (ancestorFrame != null)
			c.emit("putfield " + frameName + "/sl L" + ancestorFrame.getFrameName() + ";");
		else
			c.emit("putfield " + frameName + "/sl Ljava/lang/Object;");
		
		c.emit("astore 3");
		c.emit("\n");
		
		int order = 0;
		for (ASTNode value : ids.values()) {
			c.emit("aload 3");
			value.compile(c, newEnv);
			
			c.emit("putfield " + frameName + "/v" + order + " I");
			c.emit("\n");
			order++;
		}
		body.compile(c, newEnv);
		this.endFrame(c);
	}
	
	private void startFrame(CodeBlock c, String frame) {
		c.emit("\n");
		c.emit("new " + frame + "");
		c.emit("dup");
		c.emit("invokespecial " + frame + "/<init>()V");
		c.emit("dup");
		c.emit("aload 3");
	}
	
	private void endFrame(CodeBlock c) {
		c.emit("aload 3");
		
		Frame currentFrame = c.getCurrentFrame();
		Frame ancestorFrame = currentFrame.getAncestor();
		c.setCurrentFrame(currentFrame.getAncestor());
		
		if (ancestorFrame != null)
			c.emit("getfield " + currentFrame.getFrameName() + "/sl L" +
		ancestorFrame.getFrameName() + ";\n");
		else
			c.emit("getfield " + currentFrame.getFrameName() + "/sl Ljava/lang/Object;");
		
		c.emit("astore 3");
	}
	
}
