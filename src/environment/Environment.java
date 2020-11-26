package environment;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	Environment parent;
	Map <String, Integer> map;
	Map <String, String> varToFrame;
	
	public Environment(Environment p) {
		this.parent = p;
		map = new HashMap<String, Integer>();
		varToFrame = new HashMap<String, String>();
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
		if(map.containsKey(id))
			return map.get(id);
		return parent.find(id);
	}
	
	//Throw Id declared twice exception
	public void assoc (String id, int value) {
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
