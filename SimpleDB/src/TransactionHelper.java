import java.util.Stack;

public class TransactionHelper {
	public Stack<Memento> transactionStack;

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

	public Stack<Memento> getTransactionStack() {
		return transactionStack;
	}

	public void setTransactionStack(Stack<Memento> transactionStack) {
		this.transactionStack = transactionStack;
	}
	
	public boolean activeTransaction(){
		if (null != transactionStack && !transactionStack.isEmpty()
				&& null != transactionStack.peek()) {
			return true;
		}return false;
		}
	
}
