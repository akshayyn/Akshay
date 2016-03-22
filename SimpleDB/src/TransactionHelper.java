import java.util.Stack;

public class TransactionHelper {
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
	
	public Memento getOriginalState(){
		return transactionStack.firstElement();
	}
	
	public boolean activeTransaction(){
		if (null != transactionStack && !transactionStack.isEmpty()
				&& null != transactionStack.peek()) {
			return true;
		}return false;
		}
	
}
