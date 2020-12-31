package itypes;

public class TRef implements IType {
	IType tr;
	
	public TRef(IType t) {
		tr = t;
	}
	
	IType getreftype() {
		return tr;
	}

}
