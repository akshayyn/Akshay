
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Memento {
	private final Map<String, Integer> databaseMap;

	private final Map<Integer, Set<String>> valueConterMap;

	public Map<String, Integer> getDatabaseMap() {
		return databaseMap;
	}

	public Map<Integer, Set<String>> getValueConterMap() {
		return valueConterMap;
	}

	public Memento(Map<String, Integer> databaseMap, Map<Integer, Set<String>> valueCounterMap) {
		this.databaseMap = new HashMap<>();
		for (Entry<String, Integer> entry : databaseMap.entrySet()) {
			this.databaseMap.put(entry.getKey(), entry.getValue());
		}
		this.valueConterMap = new HashMap<>();
		for (Entry<Integer, Set<String>> entry : valueCounterMap.entrySet()) {
			this.valueConterMap.put(entry.getKey(), entry.getValue());
		}
	}

}
