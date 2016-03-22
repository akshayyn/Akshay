import java.util.Stack;
/**
 * This class is a helper class for creating save points
 * and maintaning them in a stack
 * @author Aks
 *
 */
public class TransactionHelper {
	//Stack of transactions
	private final Stack<Memento> transactionStack;

	public TransactionHelper() {
		this.transactionStack = new Stack<Memento>();
	}

	public void saveMemento(Memento memento) {
		transactionStack.push(memento);
	}

	public Memento getMemento() {
		if (null != transactionStack && !transactionStack.isEmpty()
				&& null != transactionStack.peek()) {
			return transactionStack.pop();
		}
		return null;
	}

	public void clearSavepoints() {
		transactionStack.clear();
	}
	//Returns first element of DB 
	public Memento getOriginalState(){
		return transactionStack.firstElement();
	}
	//This tels wether Stack has any active trransactions
	public boolean activeTransaction(){
		if (null != transactionStack && !transactionStack.isEmpty()
				&& null != transactionStack.peek()) {
			return true;
		}return false;
		}
	
}
