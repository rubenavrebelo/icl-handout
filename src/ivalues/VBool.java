package ivalues;

public class VBool implements IValue {
	private boolean v;
	
	public VBool(boolean v0) {
		v = v0;
	}

	public boolean getVal() {
		return v;
	}
	
	public VBool and(VBool v2) {
		return new VBool(v && v2.getVal());
	}
	
	public VBool or(VBool v2) {
		return new VBool(v || v2.getVal());
	}
}
