package ast;

import compiler.CodeBlock;
import environment.Environment;
import ivalues.IValue;
import ivalues.TypeErrorException;

public interface ASTNode {

    IValue eval(Environment env) throws TypeErrorException;
    
    void compile(CodeBlock code, Environment env);

}

