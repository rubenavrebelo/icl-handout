package compiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class Frame {

	private String frameId;
	private Frame ancestorId;
	private List<Integer> parameters;

	public Frame(Frame ancestorFrame, int nFrame, List<Integer> parameters) {
		this.frameId = "frame_"+ nFrame;
		this.ancestorId = ancestorFrame;
		this.parameters = parameters;
	}

	public String getFrameName() {
		return frameId;
	}

	public Frame getAncestor() {
		return ancestorId;
	}
	
	public List<Integer> getParameters() {
		return parameters;
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

			//TODO: .field public vX, com X = inteiro
			for (int p: parameters)
				f.println(".field public v" + "0" + " I");

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
