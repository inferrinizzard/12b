public class Dictionary {
	class Node {
		Node left, right;
		String key, value;

		public Node(String k, String v) {
			key = k;
			value = v;
			left = right = null;
		}

		public boolean end() {
			return left == null && right == null;
		}
	}

	Node root;
	int size;

	public Dictionary() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String lookup(String key) {
		return findNode(root, key) == null ? null : findNode(root, key).value;
	}

	// Node findNode(Node ptr, String key) {
	// if (ptr == null || ptr.key.equals(key))
	// return ptr;
	// // if (key.compareTo(ptr.left.key) < 0)
	// // return findNode(ptr.left, key);
	// // else
	// // return findNode(ptr.right, key);
	// Node parent = findParent(ptr);
	// if (parent.left != null && parent.left.key.equals(key))
	// return parent.left;
	// else if (parent.right != null && parent.right.key.equals(key))
	// return parent.right;
	// return null;
	// }

	Node findNode(Node R, String k) {
		if (R == null || k.compareTo(R.key) == 0)
			return R;
		if (k.compareTo(R.key) < 0)
			return findNode(R.left, k);
		else // strcmp(k, R.key)>0
			return findNode(R.right, k);
	}

	// Node findParent(Node child) {
	// Node ptr = root;
	// if (root != null && !root.equals(child)) {
	// while (!ptr.end() && !(ptr.left.key.equals(child.key) &&
	// ptr.right.key.equals(child.key)))
	// ptr = child.key.compareTo(ptr.left.key) < 0 ? ptr.left : ptr.right;
	// return ptr;
	// }
	// return root.equals(child) ? root : null;
	// }

	Node findParent(Node N) {
		Node P = null;
		if (N != root) {
			P = root;
			while (P.left != N && P.right != N) {
				if (N.key.compareTo(P.key) < 0)
					P = P.left;
				else
					P = P.right;
			}
		}
		return P;
	}

	public void insert(String key, String value) throws DuplicateKeyException {
		if (findNode(root, key) != null)
			throw new DuplicateKeyException(key);
		Node leaf = new Node(key, value);
		Node B = null;
		Node A = root;
		while (A != null) {
			B = A;
			if (key.compareTo(A.key) < 0)
				A = A.left;
			else
				A = A.right;
		}
		if (B == null)
			root = leaf;
		else if (key.compareTo(B.key) < 0)
			B.left = leaf;
		else
			B.right = leaf;
		size++;
	}

	public void delete(String key) throws KeyNotFoundException {
		Node ptr = findNode(root, key);
		Node P;
		if (ptr.left == null && ptr.right == null) { // case 1 (no children)
			if (ptr == root) {
				root = null;
			} else {
				P = findParent(ptr);
				if (P.right == ptr)
					P.right = null;
				else
					P.left = null;
			}
		} else if (ptr.right == null) { // case 2 (left but no right child)
			if (ptr == root) {
				root = ptr.left;
			} else {
				P = findParent(ptr);
				if (P.right == ptr)
					P.right = ptr.left;
				else
					P.left = ptr.left;
			}
		} else if (ptr.left == null) { // case 2 (right but no left child)
			if (ptr == root) {
				root = ptr.right;
			} else {
				P = findParent(ptr);
				if (P.right == ptr)
					P.right = ptr.right;
				else
					P.left = ptr.right;
			}
		} else { // case3: (two children: ptr.left!=null && ptr.right!=null)
			Node L = ptr.right;
			if (L != null)
				for (; L.left != null; L = L.left)
					;
			Node S = L;
			ptr.key = S.key;
			ptr.value = S.value;
			P = findParent(S);
			if (P.right == S)
				P.right = S.right;
			else
				P.left = S.right;
		}
		size--;
	}

	public void makeEmpty() {
		deleteAll(root);
		size = 0;
		root = null;
	}

	void deleteAll(Node N) {
		if (N != null) {
			Node par = findParent(N);
			deleteAll(N.left);
			deleteAll(N.right);
			if (par != null)
				par.left = par.right = null;
		}
	}

	String printTree(Node n) {
		String out = "";
		if (n != null)
			out += printTree(n.left) + " " + n.key + " " + n.value + "\n" + printTree(n.right);
		return out;
	}

	public String toString() {
		return printTree(root);
	}
}