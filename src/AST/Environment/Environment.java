package AST.Environment;

import java.util.HashMap;
import java.util.Map;

public class Environment<V> {
	Environment<V> parent;
	Map <String, V> map;
	
	public Environment(Environment<V> p) {
		this.parent = p;
		map = new HashMap<String, V>();
	}
	
	public Environment<V> beginScope() {
		Environment<V> envNew = new Environment<V>(this);
		return envNew;
	}
	
	public Environment<V> endScope() {
		return this.parent;
	}
	
	public V find(String Id) {
		
	}
	
	public void assoc (String id, V value) {
		map.put(id, value);
	}

}
