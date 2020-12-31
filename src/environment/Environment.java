package environment;

import java.util.HashMap;
import java.util.Map;
import itypes.IType;

import ivalues.IValue;

public class Environment<V> {
	Environment<V> parent;
	Map <String, V> map;
	Map <String, String> varToFrame;
	
	public Environment(Environment<V> p) {
		this.parent = p;
		map = new HashMap<String, V>();
		varToFrame = new HashMap<String, String>();
	}
	
	public Environment<V> beginScope() {
		Environment<V> envNew = new Environment<V>(this);
		return envNew;
	}
	
	public Environment<V> endScope() {
		return this.parent;
	}
	
	//Throw undeclared identifier exception
	public V find(String id) {
		if(map.containsKey(id))
			return map.get(id);
		return parent.find(id);
	}
	
	//Throw Id declared twice exception
	public void assoc (String id, V value) {
		map.put(id, value);			
	}

	public int depth() {
		return map.size();
	}
	
	public void assocFrame (String id, String frame) {
		this.varToFrame.put(id, frame);
	}
	
	public String getAssocFrame (String id) {
		return this.varToFrame.get(id);
	}
}
