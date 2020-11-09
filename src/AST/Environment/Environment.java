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
	
	public V find(String id) {
		V val = map.get(id);
		if (val == null ) {
			if (this.parent == null) {
				System.out.println("This is a parent.");
			}
			
			// find value in parent in case it's not null
			val = this.parent.find(id);
		}
		return val;
	}
	
	public void assoc (String id, V value) {
		map.put(id, value);
	}

	public int depth() {
		return map.size();
	}
}
