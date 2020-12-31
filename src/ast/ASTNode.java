package ast;

import compiler.CodeBlock;
import environment.Environment;
import itypes.IType;
import ivalues.IValue;
import ivalues.TypeErrorException;

public interface ASTNode {

    IValue eval(Environment<IValue> env) throws TypeErrorException;
    
    void compile(CodeBlock code, Environment<IValue> env);
    
	IType typecheck(Environment<IType> env) throws TypeErrorException;
}

