public class KeyNotFoundException extends Exception {
	String key = "";

	public KeyNotFoundException(String k) {
		System.out.println("Key: [" + k + "] not found");
		key = k;
	}

	public static void main(String[] args) {
	}

	public String toString() {
		return "Key: [" + key + "] not found";
	}
}