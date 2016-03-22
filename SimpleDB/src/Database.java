import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This is the main Database implementation class It Holds a HashMap which acts
 * as a in memory database
 * 
 * @author Akshay
 *
 */
public class Database {
	/** Main database Map */
	private Map<String, Integer> databaseMap;
	/** Map to hold Count of values */
	private Map<Integer, Set<String>> valueCounterMap;

	private TransactionHelper transactionHelper;

	public Database(TransactionHelper transactionHelper) {
		databaseMap = new HashMap<String, Integer>();
		valueCounterMap = new HashMap<Integer, Set<String>>();
		this.transactionHelper = transactionHelper;
	}

	/**
	 * This method supports BEGIN command on inovking it will create a save
	 * point and store in transactionHelper
	 */
	public void beginTransaction() {
		createSavePoint();
	}

	/**
	 * This MEthod supports GET KEY functionality
	 * 
	 * @param key
	 * @return String-Value
	 */
	public String getValue(String key) {
		if (!databaseMap.containsKey(key)) {
			return new String("Error: Value not found for given key");
		}
		return String.valueOf(databaseMap.get(key));
	}

	/**
	 * This supports SET KEY VALUE functionality
	 * 
	 * @param key
	 * @param value
	 */
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

	/**
	 * Supports UNSET functionality KEy-Value Pair is removed from HashMap
	 * 
	 * @param key
	 */
	public void unsetValue(String key) {
		if (databaseMap.containsKey(key)) {
			valueCounterMap.get(databaseMap.get(key)).remove(key);
			databaseMap.remove(key);
		}
	}

	/**
	 * Method for creating save point
	 */
	public void createSavePoint() {
		transactionHelper.saveMemento(new Memento(this.databaseMap, this.valueCounterMap));
	}

	/**
	 * Method for restoring DB to state givben by Memento
	 * 
	 * @param mem
	 */
	private void restore(Memento mem) {

		this.databaseMap = mem.getDatabaseMap();
		this.valueCounterMap = mem.getValueConterMap();

	}

	/**
	 * ROLLBACK functinality Memento from bottom of the stack is invoked and DB
	 * restored
	 * 
	 * @return
	 */
	public boolean rollback() {
		if (transactionHelper.activeTransaction()) {
			restore(transactionHelper.getMemento());
			return true;
		}
		return false;
	}

	/**
	 * All Mementos from Stack are removed as no save point is requiored after
	 * commit
	 * 
	 * @return
	 */
	public boolean commit() {
		if (transactionHelper.activeTransaction()) {
			transactionHelper.clearSavepoints();
			return true;
		}
		return false;
	}

	/**
	 * All non commited changes are lost' i.e. DB restored to original state
	 */
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
