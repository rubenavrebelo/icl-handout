package environment;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	Environment parent;
	Map <String, Integer> map;
	
	public Environment(Environment p) {
		this.parent = p;
		map = new HashMap<String, Integer>();
	}
	
	public Environment beginScope() {
		Environment envNew = new Environment(this);
		return envNew;
	}
	
	public Environment endScope() {
		return this.parent;
	}
	
	//Throw undeclared identifier exception
	public int find(String id) {
		int val = map.get(id);
		if ((Integer) val == null ) {
			if (this.parent == null) {
				System.out.println("This is a parent.");
			}
			
			// find value in parent in case it's not null
			val = this.parent.find(id);
		}
		return val;
	}
	
	//Throw Id declared twice exception
	public void assoc (String id, int value) {
		map.put(id, value);			
	}

	public int depth() {
		return map.size();
	}
}
