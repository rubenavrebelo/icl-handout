package ast;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import compiler.CodeBlock;
import compiler.Frame;
import environment.Environment;
import itypes.IType;
import itypes.TBool;
import itypes.TInt;
import itypes.TRef;
import ivalues.IValue;
import ivalues.TypeErrorException;

public class ASTDef implements ASTNode {

	Map<String, ASTNode> ids;
	Map<String, ASTNode> list;
	Map<String, IType> types;
	ASTNode body;
	
	public ASTDef(Map<String, ASTNode> ids, ASTNode body, Map<String, ASTNode> list) {
		this.ids = ids;
		this.body = body;
		this.list = list;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		
		Environment<IValue> env2 = env;
		env2.beginScope();
		IValue val1;
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			val1 = id.getValue().eval(env);
			env2.assoc(id.getKey(), val1);
		}
		
		IValue val2 = body.eval(env2);
		env2.endScope();
		env = env2;
		return val2;
	}
	
	public void compile(CodeBlock c, Environment<IValue> e) {
		Environment<IValue> newEnv = e.beginScope();
		List<IValue> parameters = new LinkedList<>();
		List<IType> param_types = new LinkedList<>();
		
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			try {
				parameters.add(id.getValue().eval(newEnv));
				param_types.add(types.get(id.getKey()));
			} catch (TypeErrorException e1) {
				e1.printStackTrace();
			}
		}
		
		Frame newFrame = c.createFrame(parameters, param_types);
		String frameName = newFrame.getFrameName();
		Frame ancestorFrame = newFrame.getAncestor();
		
		for(Map.Entry<String, ASTNode> id: ids.entrySet()) {
			newEnv.assocFrame(id.getKey(), frameName);
		}

		c.setCurrentFrame(newFrame);
		
		this.startFrame(c, frameName);
		
		if (ancestorFrame != null)
			c.emit("putfield " + frameName + "/sl L" + ancestorFrame.getFrameName() + ";");
		else
			c.emit("putfield " + frameName + "/sl Ljava/lang/Object;");
		
		c.emit("astore 3");
		c.emit("\n");
		
		int order = 0;
		for (Map.Entry<String, ASTNode> entry : ids.entrySet()) {
			c.emit("aload 3");
			entry.getValue().compile(c, newEnv);
			IType type = types.get(entry.getKey());
			
			if (type instanceof TInt || type instanceof TBool)
				c.emit("putfield " + frameName + "/v" + order + " I");
			else if (type instanceof TRef)
				c.emit("putfield " + frameName + "/v" + order + " Ljava/lang/Object");
			c.emit("\n");
			order++;
		}
		body.compile(c, newEnv);
		this.endFrame(c);
	}
	
	private void startFrame(CodeBlock c, String frame) {
		c.emit("\n");
		c.emit("new " + frame);
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
		ancestorFrame.getFrameName() + ";");
		else
			c.emit("getfield " + currentFrame.getFrameName() + "/sl Ljava/lang/Object;");
		
		c.emit("astore 3");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		Environment<IType> env2 = env;
		env2.beginScope();
		IType t1;
		for (Map.Entry<String, ASTNode> value: list.entrySet()) {
			t1 = value.getValue().typecheck(env);
			env2.assoc(value.getKey(), t1);
			types.put(value.getKey(), t1);
		}
		IType t2 = body.typecheck(env2);
		env2.endScope();
		env = env2;
		return t2;
	}
	
}
