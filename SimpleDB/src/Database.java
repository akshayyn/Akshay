import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {

	private Map<String, Integer> databaseMap;

	private Map<Integer, Set<String>> valueCounterMap;

	private TransactionHelper transactionHelper;

	public Database(TransactionHelper transactionHelper) {
		databaseMap = new HashMap<String, Integer>();

		valueCounterMap = new HashMap<Integer, Set<String>>();
		this.transactionHelper = transactionHelper;
	}

	public void beginTransaction() {
		createSavePoint();
	}

	public String getValue(String key) {
		if(!databaseMap.containsKey(key)){
			return new String("Error: Value not found for given key");
		}
		return String.valueOf(databaseMap.get(key));
	}

	public void setValue(String key, int value) {
		if (databaseMap.containsKey(key)) {
			valueCounterMap.get(databaseMap.get(key)).remove(key);
		}
		databaseMap.put(key, value);
		if (null != valueCounterMap) {
			if (null == valueCounterMap.get(value)) {
				Set<String> keySet = new HashSet<String>();
				keySet.add(key);
				valueCounterMap.put(value, keySet);
			} else {
				valueCounterMap.get(value).add(key);
			}
		}
	}

	public void unsetValue(String key) {

		if (databaseMap.containsKey(key)) {
			valueCounterMap.get(databaseMap.get(key)).remove(key);
			databaseMap.remove(key);

		}

	}

	public void createSavePoint() {
		transactionHelper.saveMemento(new Memento(this.databaseMap, this.valueCounterMap));
	}

	private void restore(Memento mem) {

		this.databaseMap = mem.getDatabaseMap();
		this.valueCounterMap = mem.getValueConterMap();

	}

	public boolean rollback() {
		if (transactionHelper.activeTransaction()) {
			restore(transactionHelper.getMemento());
			return true;
		}
		return false;
	}

	public boolean commit() {
		if (transactionHelper.activeTransaction()) {
			transactionHelper.clearSavepoints();
			return true;
		}
		return false;
	}

	public void end() {
		restore(transactionHelper.getOriginalState());
		transactionHelper.clearSavepoints();
	}

	public int numberOfValues(int value) {
		if (valueCounterMap.containsKey(value)) {
			return valueCounterMap.get(value).size();
		}
		return new Integer(0);
	}

	public Map<String, Integer> getDatabaseMap() {
		return databaseMap;
	}

	public void setDatabaseMap(Map<String, Integer> databaseMap) {
		this.databaseMap = databaseMap;
	}

	public Map<Integer, Set<String>> getValueConterMap() {
		return valueCounterMap;
	}

	public void setValueConterMap(Map<Integer, Set<String>> valueCounterMap) {
		this.valueCounterMap = valueCounterMap;
	}
}
