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
		if (isLn) {
			System.out.println();
			return null;
		}
		else {
			 IValue ePrint = print.eval(env);
			if (ePrint instanceof VInt)
				System.out.print(((VInt)ePrint).getVal());
			else if (ePrint instanceof VBool)
				System.out.print(((VBool)ePrint).getVal());
			return ePrint;
		}
		
//		IValue ePrint = print.eval(env);
//		if(ePrint instanceof VInt) {
//			if(isLn) {
//				System.out.print("\n");
//			}
//			System.out.print(((VInt)ePrint).getVal());
//		} else if(ePrint instanceof VBool) {
//			if(isLn) {
//				System.out.print("\n");
//			}
//			System.out.print(((VBool)ePrint).getVal());
//		}
//		return ePrint;
	}

	public void compile(CodeBlock c, Environment<IValue> e) {
		
		if (isLn)
			c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
		else {
			print.compile(c, e);
			c.emit("convert to String;");
			c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
			c.emit("call print");
//			c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
			c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType ePrint = print.typecheck(env);
		if(!(ePrint instanceof TRef))
			return ePrint;
		throw new TypeErrorException("print: can't print references");

	}

}