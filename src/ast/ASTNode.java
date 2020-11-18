package ast;

import compiler.CodeBlock;
import environment.Environment;

public interface ASTNode {

    int eval(Environment env) throws WrongValueException;
    
    void compile(CodeBlock code, Environment env);

}

