package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TRef;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VBool;
import ivalues.VInt;
import ivalues.VMCell;

public class ASTAssign implements ASTNode {

	ASTNode lhs, rhs;

	public ASTAssign(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue	v1 = lhs.eval(env);	
		if	(v1	instanceof VMCell)	{	
			IValue v2 =	rhs.eval(env);
			((VMCell)v1).set(v2);	
			return	v2;	
		}	
		throw new TypeErrorException("assignment := :lhs is not a	reference");	
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		//TODO: verificar se o rhs eh do tipo int
		lhs.compile(code, env);
		code.emit("checkcast ref_int");
		rhs.compile(code, env);
		code.emit("putfield ref_int/v I");
		
		//TODO: verificar se o rhs eh do tipo MCell
		lhs.compile(code, env);
		code.emit("checkcast ref_class");
		rhs.compile(code, env);
		code.emit("putfield ref_class/v Ljava/lang/Object");
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType v1 = lhs.typecheck(env);
		if (v1 instanceof VMCell) {
			IType v2 = rhs.typecheck(env);
			if (v2 instanceof VInt || v2 instanceof VBool)
				return new TRef(v1);
			throw new TypeErrorException("assignment: rhs argument is neither an Integer nor a Boolean");
		}
		throw new TypeErrorException("assignment: lhs argument is not a reference");
	}

}
