package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;
import ivalues.VMCell;

public class ASTRef implements ASTNode {

	ASTNode ref;
	
	public ASTRef(ASTNode ref) {
		this.ref = ref;
	}

	@Override
	public IValue eval(Environment env) throws TypeErrorException {
		IValue eRef = ref.eval(env);
		
		if (eRef instanceof VMCell) {
			return ((VMCell) eRef).get();
		}
		throw new TypeErrorException("!: argument is not a reference");
	}

	@Override
	public void compile(CodeBlock code, Environment env) {
		// TODO Auto-generated method stub
		
	}

}
