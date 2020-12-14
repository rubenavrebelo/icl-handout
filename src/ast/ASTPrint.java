package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;

public class ASTPrint implements ASTNode {
	
	ASTNode print;
	
	public ASTPrint(ASTNode node)
    {
		
    }
	
	@Override
	public IValue eval(Environment env) throws TypeErrorException
    {
		IValue ePrint = print.eval(env);
		if(ePrint instanceof VInt) {
			System.out.println(((VInt)ePrint).getVal());
		} else if(ePrint instanceof VBool) {
			System.out.println(((VBool)ePrint).getVal());
		} 
		return ePrint;
    }
	
	public void compile(CodeBlock c, Environment e) {
	}

}