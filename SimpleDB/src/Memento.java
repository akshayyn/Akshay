import java.util.Map;
import java.util.Set;


public class Memento {
	private Map<String, Integer> databaseMap;

	private Map<Integer, Set<String>> valueConterMap;

	public Map<String, Integer> getDatabaseMap() {
		return databaseMap;
	}

	public void setDatabaseMap(Map<String, Integer> databaseMap) {
		this.databaseMap = databaseMap;
	}

	public Map<Integer, Set<String>> getValueConterMap() {
		return valueConterMap;
	}

	public void setValueConterMap(Map<Integer, Set<String>> valueConterMap) {
		this.valueConterMap = valueConterMap;
	}

	public Memento(Map<String, Integer> databaseMap,
			Map<Integer, Set<String>> valueConterMap) {
		
		this.databaseMap = databaseMap;
		this.valueConterMap = valueConterMap;
	}
	
	
}
