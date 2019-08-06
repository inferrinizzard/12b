public class QueueEmptyException extends Exception {
	String name;

	public QueueEmptyException(String n) {
		name = n;
		// System.out.println(this.toString());		//edit
		// System.exit(1);			//edit
	}

	public String toString() {
		return "Queue Empty Exception! Occurred in method: " + name;
	}
}