
//Sean Song 1649139 12B/M main Class FileReverse.java
import java.io.*;
import java.util.Scanner;

public class FileReverse {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: FileReverse <input file> <output file>");
		}
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(new File(args[0]));
			out = new PrintWriter(new FileWriter(args[1]));
		} catch (IOException ex) {
			throw new Error(ex);
		}
		while (in.hasNextLine()) {
			String line = in.nextLine().trim() + " ";
			String[] token = line.split("\\s+");
			for (int i = 0; i < token.length; i++)
				out.println(stringReverse(token[i], token[i].length() - 1));
		}
		in.close();
		out.close();
	}

	static String stringReverse(String s, int i) {
		if (s.length() - 1 == i)
			return s.substring(i, i + 1);
		return stringReverse(s, i + 1) + s.charAt(i);
	}
}