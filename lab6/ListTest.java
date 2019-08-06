public class ListTest {
	public static void main(String[] args) {
		List<String> list = new List<String>();
		List<List<String>> L = new List<List<String>>();

		list.add(1, "one"); // test add
		System.out.println(list.get(1)); // test get
		L.add(1, list);
		list.remove(1);
		System.out.println(list.isEmpty());
		System.out.println(L.size());
		L.removeAll();
		System.out.println(L.size());

	}
}