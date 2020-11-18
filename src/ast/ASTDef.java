package ast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import compiler.CodeBlock;
import compiler.Frame;
import environment.Environment;

public class ASTDef implements ASTNode {

	Map<String, ASTNode> ids;
	ASTNode body;
	
	public ASTDef(Map<String, ASTNode> ids, ASTNode body) {
		this.ids = ids;
		this.body = body;
	}

	@Override
	public int eval(Environment env) throws WrongValueException {
		
		env = env.beginScope();
		int val1;
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			val1 = id.getValue().eval(env);
			env.assoc(id.getKey(), val1);
		}
		
		int val2 = body.eval(env);
		env = env.endScope();
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
			c.emit("putfield " + frameName + "/sl L" + ancestorFrame.getFrameName() + ";\n");
		else
			c.emit("putfield " + frameName + "/sl Ljava/lang/Object;\n");
		
		c.emit("astore 3\n");
		c.emit("\n");
		
		for (int i=0; i < ids.values().size(); i++) {
			c.emit("aload 3\n");
			ids.get(i).compile(c, newEnv);
			
			c.emit("putfield " + frameName + "/v" + i  + " I\n");
			c.emit("\n");
		}
		body.compile(c, newEnv);
		this.endFrame(c);
	}
	
	private void startFrame(CodeBlock c, String frame) {
		c.emit("\n");
		c.emit("new " + frame + "\n");
		c.emit("dup\n");
		c.emit("invokespecial " + frame + "/<init>()V\n");
		c.emit("dup\n");
		c.emit("aload 3\n");
		c.emit("\n");
	}
	
	private void endFrame(CodeBlock c) {
		c.emit("aload 3\n");
		
		Frame currentFrame = c.getCurrentFrame();
		Frame ancestorFrame = c.getAncestorFrame();
		c.setCurrentFrame(currentFrame.getAncestor());
		
		if (ancestorFrame != null)
			c.emit("getfield " + currentFrame.getFrameName() + "/sl L" +
		ancestorFrame.getFrameName() + ";\n");
		else
			c.emit("getfield " + currentFrame.getFrameName() + "/sl Ljava/lang/Object;\n");
		
		c.emit("astore 3\n");
	}
	
}
