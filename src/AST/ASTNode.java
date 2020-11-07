package AST;

import AST.Environment.Environment;

public interface ASTNode {

    int eval(Environment env);
	
}

