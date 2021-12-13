
public class Special1 {
	public static void main(String arg[]) {
		String para = StdIn.readString();
		// char [] in = new char[para.length()];
		char[] ch = para.toCharArray();
		balancecheck(ch);
	}

	@SuppressWarnings("unused")
	public static void balancecheck(char[] inp) {
		Stack<Character> stack = new Stack<Character>();
		int i = 1;
		stack.push(inp[0]);
		out:
		while (i < inp.length) {
			if (stack.isEmpty())
				stack.push(inp[i++]);
			while (!((int) (stack.peek()) == ((int) (inp[i]) - 2) || (int) (stack.peek()) == ((int) (inp[i]) - 1))) {
				stack.push(inp[i++]);
				if(i >= inp.length)
					break out;
			}
			while (!stack.isEmpty()
					&& ((int) (stack.peek()) == ((int) (inp[i]) - 2) || (int) (stack.peek()) == ((int) (inp[i]) - 1))) {
				if(i >= inp.length)
					break out;
				stack.pop();
				i++;
				
			}
		
		}

		if (stack.size() == 0)
			StdOut.println("The input is balanced");
		else
			StdOut.println("The input is unbalanced");
	}

}
