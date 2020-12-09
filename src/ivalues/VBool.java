package ivalues;

public class VBool implements IValue {
	private boolean v;
	
	public VBool(boolean v0) {
		v = v0;
	}

	public boolean getVal() {
		return v;
	}
}
