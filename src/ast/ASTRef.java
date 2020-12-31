package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import itypes.TRef;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VMCell;

public class ASTRef implements ASTNode {

	ASTNode ref;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public IType typecheck(Environment<IType> env) throws TypeErrorException {
		IType tRef = ref.typecheck(env);
		
		if (tRef instanceof TRef) {
			return tRef;
		}
		throw new TypeErrorException("!: argument is not a reference");
	}

}
