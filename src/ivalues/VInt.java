package ivalues;

public class VInt implements IValue {
	private int v;
	
	public VInt(int v0) {
		v = v0;
	}
	
	public int getVal() {
		return v;
	}
	
	public VInt add(VInt v2) {
		return new VInt(v + v2.getVal());
	}
	
	public VInt sub(VInt v2) {
		return new VInt(v - v2.getVal());
	}
	
	public VInt mult(VInt v2) {
		return new VInt(v * v2.getVal());
	}
	
	public VInt div(VInt v2) {
		return new VInt(v / v2.getVal());
	}
	
	public VBool eq(VInt v2) {
		return new VBool(v == v2.getVal());
	}
	
	
	public String toString() {
		return String.valueOf(v);
	}

}
