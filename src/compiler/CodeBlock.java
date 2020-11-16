package compiler;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class CodeBlock {
	private List<String> code;
	
	public static final String COMP_START = ".class public Demo\n" + ".super java/lang/Object\n" + "\n" + ";\n"
			+ "; standard initializer\n"
			+ ".method public <init>()V\n" + "   aload_0\n"
			+ "   invokenonvirtual java/lang/Object/<init>()V\n" + "   return\n" + ".end method\n" + "\r\n"
			+ ".method public static main([Ljava/lang/String;)V\n" 
			+ "       ; set limits used by this method\n"
			+ "       .limit locals 4\n" 
			+ "       .limit stack 256\n" + "\n"
			+ "       ; setup local variables:\n" + "\n"
			+ "       ;    1 - the PrintStream object held in java.lang.System.out\n"
			+ "       getstatic java/lang/System/out Ljava/io/PrintStream;\n" + "\n"
			+ "       ; place your bytecodes here\n"
			+ "       ; START\n" 
			+ "\n"
			+ "aconst_null\nastore 4\n";
	
	public static final String COMP_END =  
			"       ;END\n"
			+ "\n" + "\r\n" 
			+ "       ; convert to String;\n"
			+ "       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n" 
			+ "       ; call println \n"
			+ "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" 
			+ "\n" + "       return\n"
			+ "\n" + ".end method";
	
	public CodeBlock() {
		code = new LinkedList<>();
	}
	
	public void emit(String bytecode) {
		code.add(bytecode);
	}
	
	public void dump(PrintStream f) {
		try {
			f.println(COMP_START);
			for (String codeBlock : code) {
				f.println(codeBlock);
			}
			f.println(COMP_END);
		} catch (Exception e) {
			e.getStackTrace();
		}
	    f.close();
	}

}