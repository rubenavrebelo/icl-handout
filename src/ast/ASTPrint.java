package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TRef;
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
	
	public ASTPrint(boolean isLn)
    {
		this.isLn = isLn;
    }
	
	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException
    {
		IValue ePrint = print.eval(env);
		if(ePrint instanceof VInt) {
			if(isLn) {
				System.out.print("\n");
			}
				System.out.print(((VInt)ePrint).getVal());
		} else if(ePrint instanceof VBool) {
			if(isLn) {
				System.out.print("\n");
			}
			System.out.print(((VBool)ePrint).getVal());
		} 
		return ePrint;
    }
	
	public void compile(CodeBlock c, Environment<IValue> e) {
		//TODO
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType ePrint = print.typecheck(env);
		if(!(ePrint instanceof TRef))
			return ePrint;
		throw new TypeErrorException("print: can't print references");

	}

}