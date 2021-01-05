package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TInt;
import itypes.TRef;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VMCell;

public class ASTRef implements ASTNode {

	ASTNode ref;
	IType type;

	public ASTRef(ASTNode ref) {
		this.ref = ref;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws TypeErrorException {
		IValue eRef = ref.eval(env);

		if (eRef instanceof VMCell) {
			return ((VMCell) eRef).get();
		}
		throw new TypeErrorException("!: argument is not a reference");
	}

	@Override
	public void compile(CodeBlock code, Environment<IValue> env) {
		if (type instanceof TInt) {
			ref.compile(code, env);
			code.emit("checkcast ref_int");
			code.emit("getfield ref_int/v I");
		}
		else if (type instanceof TRef) {
			ref.compile(code, env);
			code.emit("checkcast ref_class");
			code.emit("getfield ref_class/v Ljava/lang/Object");
		}
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType tRef = ref.typecheck(env);

		if (tRef instanceof TRef) {
			type = tRef;
			return type;
		}
		throw new TypeErrorException("!: argument is not a reference");
	}

}
