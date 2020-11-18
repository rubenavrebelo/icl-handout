package ast;

import compiler.CodeBlock;
import compiler.Frame;
import environment.Environment;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id)
    {
		this.id = id;
    }
	
	@Override
	public int eval(Environment env)
    {
		return env.find(id);
	}
	
	public void compile(CodeBlock c, Environment e) {
		c.emit("aload 3\r\n");
		
		Frame currentFrame = c.getCurrentFrame();
		Frame ancestorFrame = null;
		
		if (currentFrame != null)
			ancestorFrame = currentFrame.getAncestor();
		
		//TODO
		while (ancestorFrame != null) {
			c.emit("getfield " + currentFrame.getFrameName() + "/sl L" +
		ancestorFrame.getFrameName() + ";\n");
			currentFrame = ancestorFrame;
			ancestorFrame = currentFrame.getAncestor();
		}
		
		if (currentFrame != null) {
			for (int p: currentFrame.getParameters())
				c.emit("getfield " + currentFrame.getFrameName() + "/v" + p + "I\n");
		}
		c.emit("\n");
	}

}