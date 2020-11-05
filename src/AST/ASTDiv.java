package AST;

public class ASTDiv implements ASTNode {
	
	ASTNode lhs, rhs;
	
	public ASTDiv(ASTNode l, ASTNode r)
    {
			lhs = l; rhs = r;
    }
	 
	public int eval()
    { 
    	int v1 = lhs.eval();
		int v2 = rhs.eval();
        return v1 / v2; 
	}

}