import java.util.Scanner;


public class DatabaseUI {
	public static void main(String[] args) {
		Database db = new Database(new TransactionHelper());
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\\s+"); // space delimited
		String cmdLine; // cmdLine is typically 'cmd' followed by 'key' followed by 'value'
		while (scanner.hasNextLine()) {
			cmdLine = scanner.nextLine();
			String[] tokens = cmdLine.split("\\s+");
			String cmd = tokens[0];
			String name;
			Integer value;
			try {
				switch (cmd) {
				case "GET":
					name = tokens[1];
					
					System.out.println(db.getValue(name) != null ? db.getValue(name):"NULL");
					break;
				case "SET":
					name = tokens[1];
					value = Integer.parseInt(tokens[2]);
					db.setValue(name, value);
					
					break;
				case "UNSET":
					name = tokens[1];
					db.unsetValue(name);
					
					break;
				case "NUMEQUALTO":
					value = Integer.parseInt(tokens[1]);
					System.out.println(db.numberOfValues(value));
					break;
				case "BEGIN":
					db.beginTransaction();
					break;
				case "ROLLBACK":
					if (!db.rollback()) System.out.println("NO TRANSACTION");
					break;
				case "COMMIT":
					if (!db.commit()) System.out.println("NO TRANSACTION");
					break;					
				case "END":
					return;
				case "":
					break;
				default:
					System.out.println("Invalid command: " + cmd );
				}
			} catch (NumberFormatException e) {			// SET n a
				System.out.println("Invalid number format: " + cmdLine );
			} catch (ArrayIndexOutOfBoundsException e) {// GET
				System.out.println("Possibly missing operand: " + cmdLine );
			}
		}
		scanner.close();
	}
}
