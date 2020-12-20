package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;

public class ASTPrint implements ASTNode {
	
	ASTNode print;
	boolean isLn;
	
	public ASTPrint(ASTNode node, boolean isLn)
    {
		print = node;
		this.isLn = isLn;
    }
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException
    {
		IValue ePrint = print.eval(env);
		if(ePrint instanceof VInt) {
			if(isLn) {
				System.out.println(((VInt)ePrint).getVal());
			}
				System.out.print(((VInt)ePrint).getVal());
		} else if(ePrint instanceof VBool) {
			if(isLn) {
				System.out.println(((VBool)ePrint).getVal());
			}
			System.out.print(((VBool)ePrint).getVal());
		} 
		return ePrint;
    }
	
	public void compile(CodeBlock c, Environment e) {
		//TODO
	}

}