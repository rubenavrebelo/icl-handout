package compiler;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	private List<String> code;
	
	public CodeBlock() {
		code = new LinkedList<>();
	}
	
	public void emit(String bytecode) {
		code.add(bytecode);
	}
	
	public void dump(PrintStream f) {
		try {
			for (String codeBlock : code) {
				f.println(codeBlock);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	    f.close();
	}

}