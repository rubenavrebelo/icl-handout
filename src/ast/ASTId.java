package ast;

import compiler.CodeBlock;
import compiler.Frame;
import environment.Environment;
import ivalues.IValue;

public class ASTId implements ASTNode {
	
	String id;
	
	public ASTId(String id)
    {
		this.id = id;
    }
	
	@Override
	public IValue eval(Environment env)
    {
		return env.find(id);
	}
	
	public void compile(CodeBlock c, Environment e) {
		c.emit("aload 3");
		
		Frame currentFrame = c.getCurrentFrame();
		Frame ancestorFrame = null;
		
		if (currentFrame != null)
			ancestorFrame = currentFrame.getAncestor();
		
		while (ancestorFrame != null && currentFrame.getFrameName() != e.getAssocFrame(id)) {
			c.emit("getfield " + currentFrame.getFrameName() + "/sl L" +
		ancestorFrame.getFrameName() + ";");
			currentFrame = ancestorFrame;
			ancestorFrame = currentFrame.getAncestor();
		}
		
		if (currentFrame != null) {
			int i = 0;
			for (IValue p: currentFrame.getParameters()) {
				
				c.emit("getfield " + currentFrame.getFrameName() + "/v" + i + " I");
				i++;
			}
		}
		c.emit("\n");
	}

}