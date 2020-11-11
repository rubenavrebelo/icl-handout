package AST;

import AST.Environment.Environment;
import compiler.CodeBlock;

public interface ASTNode {

    int eval(Environment env);
    
    void compile(CodeBlock c, Environment e);
	
}

