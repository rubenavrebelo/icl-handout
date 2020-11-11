package compiler;

import java.io.PrintStream;
import java.util.List;

public class CodeBlock {
	private List<String> code;
	
	public void emit(String bytecode) {
		code.add(bytecode);
	}
	
	public void dump(PrintStream f) {
		
		try {
		}
	}

}