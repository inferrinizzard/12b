//Sean Song 1649139 12B/M 9/4 main Class	Subset.java

public class Subset {
	static String setToString(int[] B) {
		String result = "{";
		for (int i = 1; i < B.length; i++)
			result += (B[i] == 1 ? i + ", " : "");
		return (result.length() > 2 ? result.substring(0, result.length() - 2) : result) + "}";
	}

	static void printSubsets(int[] B, int k, int i) {
		if (k == 0)
			System.out.println(setToString(B));
		else if (k > B.length - i)
			return;
		else {
			int[] temp = B.clone();
			temp[i] = 1;
			printSubsets(temp, k - 1, i + 1);
			printSubsets(B, k, i + 1);
		}
	}

	public static void main(String[] args) {
		int n, k;
		try {
			if (args.length != 2) {
				System.out.println("Usage: Java Subset <int> <int>");
				return;
			}
			n = Integer.parseInt(args[0]);
			k = Integer.parseInt(args[1]);
		} catch (NumberFormatException ex) {
			System.out.println("Usage: Java Subset <int> <int>");
			return;
		}
		if (0 > k || k > n) {
			System.out.println("Usage: Java Subset <int> <int>");
			return;
		}
		printSubsets(new int[n + 1], k, 1);
	}
}