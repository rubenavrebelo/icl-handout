package AST.Environment;

import java.util.Map;

public class Environment<V> {
	Environment<V> parent;
	Map <String, V> map;
	
	public Environment(Environment<V> p) {
		
	}
	
	public Environment<V> beginScope() {
		
	}
	
	public Environment<V> endScope() {
		
	}
	
	public V find(String Id) {
		
	}
	
	public void assoc (String id, V value) {
		
	}

}
