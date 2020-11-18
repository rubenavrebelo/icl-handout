package compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		try {
			File newFile = new File(frameId + ".j");

			FileWriter fw = new FileWriter(newFile);
			fw.write(".class public frame_" + frameId + "\n");
			fw.write(".super java/lang/Object\n");
			if (ancestorId != null)
				fw.write(".field public sl L" + ancestorId.getFrameName() + ";\n");
			else
				fw.write(".field public sl Ljava/lang/Object;\n");

			//TODO: .field public vX, com X = inteiro
			for (int p: parameters)
				fw.write(".field public v" + p + " I\n");

			fw.write("\n");
			fw.write(".method public <init>()V\n");
			fw.write("	aload_0\n");
			fw.write("	invokenonvirtual java/lang/Object/<init>()V\n");
			fw.write("	return\n");
			fw.write(".end method\n");
			fw.write("\n");

			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
