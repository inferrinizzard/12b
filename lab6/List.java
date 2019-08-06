public class List<T> {
	public Node head;
	int size;

	class Node {
		T item;
		Node next;
		int index;

		public Node(T t, int i) {
			item = t;
			next = null;
			index = i;
		}
	}

	public List() {
		head = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void add(int i, T newItem) throws ListIndexOutOfBoundsException {
		if (size > 0 && i > size + 1 || i < 1)
			throw new ListIndexOutOfBoundsException("" + i);
		Node ptr = head;
		Node temp = new Node(newItem, i);
		if (head == null)
			head = temp;
		else {
			while (ptr.index != i - 1)
				ptr = ptr.next;
			ptr.next = temp;
			temp.next = ptr.next;
		}
		size++;
	}

	public T get(int i) throws ListIndexOutOfBoundsException {
		if (size > 0 && i > size || i < 1)
			throw new ListIndexOutOfBoundsException("" + i);
		Node ptr = head;
		while (ptr != null)
			if (ptr.index == i)
				return ptr.item;
			else
				ptr = ptr.next;
		return null;
	}

	public void remove(int i) throws ListIndexOutOfBoundsException {
		if (i > size || i < 1)
			throw new ListIndexOutOfBoundsException("" + i);
		Node ptr = head;
		if (i == 1)
			head = head.next;
		else {
			while (ptr.index < i)
				ptr = ptr.next;
			ptr.next = ptr.next.next;
		}
		size--;
	}

	public void removeAll() {
		Node ptr = head;
		while (size > 0) {
			Node next = ptr.next;
			ptr.next = null;
			ptr = next;
			size--;
		}
	}

	public String toString() {
		String result = "";
		Node ptr = head;
		for (int i = 0; i < size; i++) {
			result += ptr.item.toString() + " ";
			ptr = ptr.next;
		}
		return result;
	}

}