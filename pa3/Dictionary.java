import java.util.List;

public class Dictionary implements DictionaryInterface {

	public class Node {
		Node next;
		String key, value;

		public Node(Node n, String k, String v) {
			next = n;
			key = k;
			value = v;
		}
	}

	Node start;

	public Dictionary() {
		start = new Node(null, "", "");
	}

	// isEmpty()
	// pre: none
	// returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty() {
		return start.next == null;
	};

	Node findKey(String key) {
		Node cur = start;
		while (cur.next != null) {
			if (cur.key.equals(key))
				return cur;
			cur = cur.next;
		}
		return null;
	}

	// size()
	// pre: none
	// returns the number of entries in this Dictionary
	public int size() {
		int size = 0;
		Node cur = start;
		while (cur.next != null) {
			size++;
			cur = cur.next;
		}
		return size;
	}

	// lookup()
	// pre: none
	// returns value associated key, or null reference if no such key exists
	public String lookup(String key) {
		return findKey(key) == null ? null : findKey(key).value;
	}

	// insert()
	// inserts new (key,value) pair into this Dictionary
	// pre: lookup(key)==null
	public void insert(String key, String value) throws DuplicateKeyException {
		if (findKey(key) != null)
			throw new DuplicateKeyException(key);
		Node cur = start;
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = new Node(null, key, value);
	}

	// delete()
	// deletes pair with the given key
	// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException {
		Node node = findKey(key);
		if (node == null)
			throw new KeyNotFoundException(key);
		Node prev = null;
		Node cur = start;
		while (cur.next != null) {
			if (cur.next == node) {
				prev = cur;
				break;
			}
			cur = cur.next;
		}
		prev.next = node.next;
	}

	// makeEmpty()
	// pre: none
	public void makeEmpty() {
		start.next = null;
	}

	// toString()
	// returns a String representation of this Dictionary
	// overrides Object's toString() method
	// pre: none
	public String toString() {
		Node cur = start;
		String result = "";
		while (cur.next != null) {
			result += cur.key + " " + cur.value + "\n";
			cur = cur.next;
		}
		return result;
	}
}