package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import itypes.IType;
import ivalues.IValue;

public class Frame {

	private String frameId;
	private Frame ancestorId;
	private List<IValue> parameters;
	private List<IType> param_types;

	public Frame(Frame ancestorFrame, int nFrame, List<IValue> parameters, List<IType> param_types) {
		this.frameId = "frame_"+ nFrame;
		this.ancestorId = ancestorFrame;
		this.parameters = parameters;
		this.param_types = param_types;
	}

	public String getFrameName() {
		return frameId;
	}

	public Frame getAncestor() {
		return ancestorId;
	}
	
	public List<IValue> getParameters() {
		return parameters;
	}
	
	public List<IType> getParamTypes(){
		return param_types;
	}

	public void writeFrame() {
		PrintStream f;
		try {
			f = new PrintStream(new FileOutputStream(frameId + ".j"));
			f.println(".class public " + frameId);
			f.println(".super java/lang/Object");
			if (ancestorId != null)
				f.println(".field public sl L" + ancestorId.getFrameName() + ";");
			else
				f.println(".field public sl Ljava/lang/Object;");

			int i = 0;
			for (IValue p: parameters) {
				f.println(".field public v" + i + " " + param_types.get(i));
				i++;
			}

			f.println("\n");
			f.println(".method public <init>()V");
			f.println("aload_0");
			f.println("invokenonvirtual java/lang/Object/<init>()V");
			f.println("return");
			f.println(".end method");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
