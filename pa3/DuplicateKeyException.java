public class DuplicateKeyException extends Exception {
	String key;

	public DuplicateKeyException(String k) {
		System.out.println("Duplicate key : [" + k + "] found");
		key = k;
	}

	public String toString() {
		return "Duplicate key : [" + key + "] found";
	}

}