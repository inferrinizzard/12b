//Sean Song 1649139 ssong28 12B/M 17/4 Queens main Java file
public class Queens {
	static int findSolutions(int[][] B, int i, String mode) {
		int sum = 0;
		if (i > B.length - 1) {
			if (mode == "verbose")
				printBoard(B);
			return B.length > 1 ? 1 : 0;
		}
		for (int j = 1; j < B.length; j++)
			if (B[i][j] == 0) {
				placeQueen(B, i, j);
				sum += findSolutions(B, i + 1, mode);
				removeQueen(B, i, j);
			}
		return sum;
	}

	static void printBoard(int[][] B) {
		// for (int[] a : B) {
		// for (int b : a) {
		// System.out.print(b + " ");
		// }
		// System.out.println();
		// }
		// System.out.println();
		String result = "(";
		for (int[] row : B)
			for (int j = 1; j < row.length; j++)
				if (row[j] == 1)
					result += j + ", ";
		System.out.println((result.length() > 2 ? result.substring(0, result.length() - 2) : "(") + ")");
	}

	static void placeQueen(int[][] B, int i, int j) {
		if (B.length < i || B[0].length < j)
			return;
		for (int x = 1; x < B.length; x++) {
			B[i][x]--;
			B[x][j]--;
			for (int y = 1; y < B[0].length; y++) {
				if (x - i == y - j || x - i == j - y)
					B[x][y]--;
			}
		}
		B[i][j] = 1;
		B[i][0] = j;
	}

	static void removeQueen(int[][] B, int i, int j) {
		if (B.length < i || B[0].length < j || B[i][j] != 1)
			return;
		for (int x = 1; x < B.length; x++) {
			B[i][x]++;
			B[x][j]++;
			for (int y = 1; y < B[0].length; y++) {
				if (x - i == y - j || x - i == j - y)
					B[x][y]++;
			}
		}
		B[i][j] = 0;
		B[i][0] = 0;
	}

	public static void main(String[] args) {
		int n = 0;
		try {
			n = (args[0].equals("-v")) ? Integer.parseInt(args[1]) : Integer.parseInt(args[0]);
			// if(Integer.parseInt(args[0] == 0 && args[0] != "0" || !args[0].equals("-v"))
			// throw new Exception();
		} catch (Exception e) {
			System.err.println("Usage: Queens [-v] number\nOption: -v verbose output, print all solutions");
			return;
		}
		int[][] B = new int[n + 1][n + 1];
		int sol = findSolutions(B, 1, (args[0].equals("-v")) ? "verbose" : "");
		System.out.println(n + "-Queens has " + sol + " solutions");
	}
}