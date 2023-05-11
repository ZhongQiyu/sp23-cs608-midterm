import java.util.Stack;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.text.DecimalFormat;

import java.io.IOException;
import java.util.EmptyStackException;

/*
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
*/

/*
The implementation of a to take calculations including:
- addition
- subtraction
- multiplication
- division
- regression analysis
- *power
- *boolean-based operation (AND, OR, XOR, etc.)
- *differentiation
- *integration
- etc.
Basically PEMDAS, but the results would differ.
The final result would be displayed in the screen.
* means that the function is under implementation.
*/
public class FinanceCalculator { // add generic

	/*
	A fetcher for the real-time exchange rate
	from a type of currency to the other.
	public class ExchangeRateFetcher {

	    private static final String API_URL = "https://api.exchangeratesapi.io/latest?base=USD";

	    public static void main(String[] args) {
	        OkHttpClient client = new OkHttpClient();
	        Request request = new Request.Builder()
	                .url(API_URL)
	                .build();

	        try (Response response = client.newCall(request).execute()) {
	            if (!response.isSuccessful()) {
	                System.err.println("Failed to fetch exchange rates");
	                return;
	            }

	            String responseBody = response.body().string();
	            JSONObject jsonObject = new JSONObject(responseBody);
	            JSONObject rates = jsonObject.getJSONObject("rates");

	            System.out.println("USD to EUR: " + rates.getDouble("EUR"));
	            System.out.println("USD to GBP: " + rates.getDouble("GBP"));
	            System.out.println("USD to JPY: " + rates.getDouble("JPY"));
	            // ... 获取其他货币汇率

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	*/

	/*
	The default class of Node that needs to be
	used to represent the node of binary tree.
	*/
	public class Node<T> {

		// the concatenated declaration of the class does not permit
	    private T data;
	    private Node<T> left;
	    private Node<T> right;

	    /* The default constructor to initialize nodes. */
	    public Node() {
	    	this.data = null;
	    	this.left = null;
	    	this.right = null;
	    }

	    /*
	    Non-default constructor of the node.
	    Assign data to the new node.
	    Set left and right children to null.
	    */
	    public Node(T data) {
	        this.data = data;
	        this.left = null;
	        this.right = null;
		}

		/* Get the data from the existing node. */
		public T getData() { return this.data; }

		/* Get the left children of the existing node. */
		public Node<T> getLeft() { return this.left; }

		/* Get the right children of the existing node. */
		public Node<T> getRight() { return this.right; }

		/* Set the data of the original node. */
		public void setData(T data) { this.data = data; }

		/* Set the data of the left node. */
		public void setLeft(Node<T> left) { this.left = left; }

		/* Set the data of the right node. */
		public void setRight(Node<T> right) { this.right = right; }

		/*
		Return the String that was placed within the node.
		This case would not lead us to the case of null-pointer
		since all of the RPN would be converted to a full tree.
		*/ 
		public String toString() {
			String contents = "";
			contents += LTP;
			contents += left != null ? left.toString() : ""; // add the contents of the left node
			contents += data.toString(); // add the contents of the root node
			contents += right != null ? right.toString() : ""; // add the contents of the right node
			contents += RTP;
			return contents;
		}

	}

	/* The space for static variables. */
	private static final int CONV_ARGS_CT = 3;
	private static final int HIGH = 3;
	private static final int MEDIUM = 2;
	private static final int LOW = 1;
	private static final char ADD = '+';
	private static final char SUB = '-'; // differentiate subtract and negation
	private static final char MUL = '*';
	private static final char DIV = '/';
	private static final char LTP = '(';
	private static final char RTP = ')';
	private static final String PWR = "^^";
	private static final char AND = '&';
	private static final char OR = '|';
	private static final char XOR = '^';
	private static final char SP = ' ';	
	private static final ArrayList<Character> NUMS = new ArrayList<Character>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
	private static final Hashtable<String, Double> CRCY = new Hashtable<String, Double>() {{
        put("CNY",1.0000);
        put("USD",6.9230);
        put("INR",0.0840);
        put("UKP",8.7100);
        put("EUR",7.5800);
        put("JPY",0.0510);
        put("CAD",5.1700);
        put("AUD",4.6700);
        put("HKD",0.8800);
        put("TRY",0.3500);
    }};
    private String expression; // need to use this

	/* Default constructor of an calculator. */
	public FinanceCalculator() {
		this.expression = " ";
	}

	/* Non-default constructor of an calculator. */
	public FinanceCalculator(String expression) {
		// call distribute() to initialize numbers and operators
		this.expression = expression;
	}

	/* Return the expression that was put within the stage of the String. */
	public String getExpression() { return this.expression; } 

	/*
	Test if the expression is a valid one. By means of validity,
	the open-parenthesis and the closing one must have the right
	order and a correct count of matching points. The balanced
	can then be achieved with brackets.
	*/
	public static boolean isBalanced(String expr) {
        // 使用 Stack 来保存括号
        Stack<Character> stack = new Stack<>();
        int count = expr.length();
        for (int i = 0; i < count; i++) {
            char x = expr.charAt(i);
            if (x == '(' || x == '[' || x == '{') {
                // 当遇到开括号时，将其压入栈中
                stack.push(x);
                continue;
            }
            // 当遇到闭括号时，如果栈为空，说明没有相应的开括号
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }
        // 如果栈为空，说明所有的开括号都被正确的闭括号匹配了
        return (stack.isEmpty());
    }

	/*
	Compute the priority of different operators.
	*/	
	private int getPriority(char op) {
		int priority = 0;
		switch (op) {
		case MUL:
		case DIV:
			priority = HIGH;
			break;
		case ADD:
		case SUB:
			priority = MEDIUM;
			break;
		case LTP:
		case RTP:
			priority = LOW;
		}
		return priority;
	}

	/*
	Compare the Strings together so that the priorities would
	be computed.
	*/
	private int compareTo(char op1, char op2) {
		int pValue1 = getPriority(op1);
		//System.out.println("The first character " + String.valueOf(op1) + " comes to be with a priority of " + Integer.toString(pValue1) + ".");
		int pValue2 = getPriority(op2);
		//System.out.println("The second character " + String.valueOf(op2) + " comes to be with a priority of " + Integer.toString(pValue2) + ".");
		return pValue1 < pValue2 ? 1:-1;
	}

	/*
	Compute the result of a given type of operation and
	two numbers involved in the operations.
	*/
	private double compute(double a, double b, char op) {
		double result = 0.0;
		switch (op) {
		case ADD:
			System.out.println("We are adding " + a + " and " + b + ".");
			result = a + b;
			break;
		case SUB:
			System.out.println("We are subtracting " + a + " from " + b + ".");
			result = a - b;
			break;
		case MUL:
			System.out.println("We are multiplying " + a + " with " + b + ".");
			result = a * b;
			break;
		case DIV:
			System.out.println("We are dividng " + a + " by " + b + ".");
			result = a / b;
			break;
		}
		return result;
	}

	/*
	Trim the original expression so that there is no space
	between any of the operators within the expression.
	*/
	private String parseNegativeNumber() {
		String expression = this.getExpression();
		String parsed = ""; //
		return parsed;
	}

	/*
	Convert an arithmetic expression into Reverse-Polish Notation.
	*/
	private ArrayList<Character> getRPN(String expression) {
		// 创建一个结果列表和一个操作符堆栈。
		Stack<Character> operators = new Stack<>();
		ArrayList<Character> results = new ArrayList<>();
		char current = ' ';
		int len = expression.length();
		int index;
		// 从左到右扫描算式中的每个字符。
		for (index = 0; index < len; index++) {
			current = expression.charAt(index);
			switch (current) {
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				//如果它是一个数字，则将其输出到结果列表中。
				//System.out.println("The current character is a number, being " + current + ".");
				results.add(current);
				break;
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				//如果它是一个操作符，比较其与操作符堆栈顶部的操作符的优先级。
				//如果操作符堆栈顶部的操作符优先级高于或等于该操作符，则弹出堆栈中的操作符，将它们输出到结果列表中。
				//System.out.println("The current character is an operator, being " + current + ".");
				int cmpRst;
				try{
				  	cmpRst = this.compareTo(operators.peek(),current);
				}catch(EmptyStackException e){
				  	System.out.println("The stack is currently empty.");
				  	operators.push(current);
				  	continue;
				}
				if (cmpRst == -1) { // if the current operator's priority is larger than or equal to that of the stack's top
					results.add(operators.pop());
					//System.out.println("The current character is not as prioritized as the operators do.");
				}
				//然后将该操作符压入堆栈中。
				operators.push(current);
				//重复此步骤直到操作符堆栈为空或堆栈顶部的操作符优先级低于该操作符。
				break;
			case LTP:
				//如果它是一个左括号，则将其压入堆栈中。
				//System.out.println("LTP recognized.");
				operators.push(current);
				//System.out.println("LTP stored.");
				break;
			case RTP:
				//如果它是一个右括号，则弹出堆栈中的操作符，并将它们输出到结果列表中，直到遇到左括号为止。
				//System.out.println("RTP recognized.");
				char currOp = operators.peek();
				while (currOp != LTP) {
					results.add(operators.pop());
					currOp = operators.peek();
				}
				if (currOp == LTP) {
					operators.pop(); //左括号只弹出，不输出到结果列表中。
					//System.out.println("LTP popped.");
				}
				break;
			case SP:
				//System.out.println("The current character is a space.");
			}
			//重复步骤 2-5 直到扫描完整个算式。
			System.out.println("The current operator stack is " + operators.toString() + ".");
			System.out.println("The current array of results is " + results.toString() + ".\n");
		}
		//如果操作符堆栈中还有操作符，则依次弹出堆栈中的操作符，并将它们输出到结果列表中。
		while (!operators.isEmpty()) {
			results.add(operators.pop());
		}
		//将结果列表中的元素按顺序输出，即可得到逆波兰表达式。
		return results;
	}

	/*
	**
	*/
	private String toRealRPN(ArrayList<Character> RPNExpression) {
		StringBuilder sb = new StringBuilder();
        for (char c : RPNExpression) {
            sb.append(c);
            // 如果需要在元素之间添加分隔符，可以取消注释下一行
            sb.append(" ");
        }
        // 如果在元素之间添加了分隔符，请删除最后一个分隔符
        int lastSeparatorIndex = sb.lastIndexOf(" ");
        if (lastSeparatorIndex != -1) {
            sb.delete(lastSeparatorIndex, sb.length());
        }
        return sb.toString();
	}

	/*
	Evaluate the results given a RPN expressions that
	contains numbers and operators. It is converted
	for the sake of helping the evaluation so that a
	binary-tree like structure would be used to store
	the notations.
	arg RPNExpression: an RPN expression to convert from.
	*/
	private Stack<Object> evaluate(String rpn) { // change the return type to be at least double
		//1.初始化一个空栈。
		int count = rpn.length();
		Stack<Object> operators = new Stack<>();
		//2.从左到右遍历逆波兰表达式的每个元素。
		int index;
		char currentNotation = ' '; // generically store every single item to be passed in
		// Omega(n^2)
		for (index = 0; index < count; index++) {
			currentNotation = rpn.charAt(index);
			//System.out.println("The current notation is: " + currentNotation + ".");
			switch (currentNotation) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				//a. 如果当前元素是操作数（数字），则创建一个新的二叉树节点，将其值设置为该操作数，并将节点压入栈中。
				Node<Character> newNode = new Node<>(currentNotation);
				operators.push(newNode); // call Integer.valueOf() later in the computation operations
				//System.out.println("A node of a BST is created: " + newNode.toString());
				//System.out.println("We push the number " + currentNotation + " into the number stack.");
				//handle negative numbers
				break;
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				//b. 如果当前元素是操作符（如 +、-、*、/ 等），则从栈中弹出两个节点，作为当前操作符的左右子节点。
				Node<Character> right = (Node<Character>) operators.pop(); // right first
				Node<Character> left = (Node<Character>) operators.pop(); // then left
				//创建一个新的二叉树节点，将其值设置为该操作符，并将左右子节点分别连接到该节点。
				Node<Character> opNode = new Node<>(currentNotation);
				opNode.setLeft(left);
				opNode.setRight(right);
				//System.out.println("We set up a new node whose data is the operator, with children being the two popped nodes.");
				//然后将新创建的节点压入栈中。
				operators.push(opNode); // Stack<Node>? change the data type
				//System.out.println("We push the operator " + currentNotation + " into the operator stack.");
				break;
			case SP:
				//System.out.println("The current entry is a space, and we have skipped this.\n");
				continue;
			}
			System.out.println("The current stack is: " + operators.toString() + ".\n");
		}
		//3.当遍历完成后，栈顶元素将是二叉树的根节点。
		return operators;
	}

	/* Perform a pre-order traversal upon the binary search tree. */
	private double DFS(Node<Object> evaluated) {
		Node<Object> current = evaluated;
		char op = (char) current.getData(); // the value of any non-leaf node would always be an operator
		double result = 0.0;
		Node<Object> left = current.getLeft();
		Node<Object> right = current.getRight();
		if (current == null) return 0.0; // base case
		else if (left == null || right == null) return Double.parseDouble(Character.toString((char)current.getData())); // mid
		else { // here there would not be a case for null
			Object leftVal = left.getData();
			Object rightVal = right.getData();
			if ((leftVal instanceof Number) && (rightVal instanceof Number)) {
				double leftNum = (double) leftVal;
				double rightNum = (double) rightVal;
				result += compute(leftNum, rightNum, op);
			}
			else result += compute(DFS(left), DFS(right), op); // left-right
			return result;
		}
	}

	/*
	Compute the exact value given an arithmetic expression.
	*/
	public double compute(String expression) {
		System.out.println("Step 1: getRPN");
		ArrayList<Character> RPNExpression = this.getRPN(expression);
		System.out.println("RPNExpression: " + RPNExpression + "\n");

		System.out.println("Step 2: toRealRPN");
		String rpn = this.toRealRPN(RPNExpression);
		System.out.println("rpn: " + rpn + "\n");

		System.out.println("Step 3: evaluate the RPN as a BST");
		Stack<Object> tree = this.evaluate(rpn);
		System.out.println("tree: " + tree + "\n");

		System.out.println("Step 4: take DFS through the BST");
		Node<Object> evaluated = (Node<Object>) tree.pop();
		double result = this.DFS(evaluated);
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("result: " + df.format(result) + "\n");
		return result;
	}

	/*
	Convert a given amount of money from a type
	of currency to another. The rate is based
	with in-time gleaning of data on the website.
	*/
	public static double currencyConversion(String[] convArgs) {
		// clean the user input
        String from = convArgs[0];
        String to = convArgs[1];
        double amount = Double.parseDouble(convArgs[convArgs.length-1]);
        // make the conversion
		String output = " ";
		double fromRate = CRCY.get(from);
		double toRate = CRCY.get(to);
		double total = fromRate / toRate * amount;
		// #.00 表示两位小数
		DecimalFormat df = new DecimalFormat("#0.00"); // 保留两位小数，四舍五入
		System.out.println("We are converting " + df.format(amount) + " " + from + " to " + to + ".\nThis gives us a total of " + df.format(total) + " " + to + ".\n");
		return total;
	}

	/*
	Compute the amount that the credit card balance
	would need to return amounts from the user. It
	gives an estimation of the total amount of time
	where the pay-back would be complete.
	*/
	public static double creditCardPayoff(float balance, float ir, String[] paymentArgs) {
		// Calculator
		return 0.0;
	}
	
	/*
	Calculate the monthly payment that would needed to be made
	given a set of parameters, being the loan amount, the rate
	annually for return-interest, and the count of loan terms.
	*/
	public static double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int loanTerm) {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        int numberOfPayments = loanTerm * 12;
        double discountFactor = (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1)
                / (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments));
        return loanAmount / discountFactor;
    }

	/*
	Display the information that is stored in the calculator.
	*/
	public String toString() {
		// print the base information of the calculator, mainly the expression
		String baseInfo = "Expression: " + this.getExpression() + "\n";
		// print the information for the menu
		String menuInfo = "Option 1: Regular Mode ~ The Algebraic Calculator (+, -, *, /)\n" +
						  "Option 2: Flexible Mode ~ The Currency Converter and Currency Payment Planner\n" +
						  "Option 3: Advanced Mode ~ The Mini-sized Scientific Computer (in-dev)\n";
		return baseInfo + menuInfo;
	}

	/* --FOR TESTS START-- */

	/* Generate a node. */
	public Node<Object> genNode(Object data) { return new Node<Object>(data); }

	/* Return the node that was set with a children node. */
	public Node<Object> genChildren(Object rootData, Object childrenData, String dir) {
		Node<Object> rootNode = new Node<>(rootData);
		Node<Object> childrenNode = new Node<>(childrenData);
		// set with assumed robustness in input
		if (dir.equals("L")) rootNode.setLeft(childrenNode);
		else rootNode.setRight(childrenNode);
		return rootNode;
	}

	/* --FOR TESTS END-- */

	public void testCurrencyConversion() {
		// add logic to handle the difference
		Scanner sc = new Scanner(System.in);
		String[] convArgs = new String[CONV_ARGS_CT];
		String userCommand = " ";
		System.out.println("Test Started.\n");
		while (!userCommand.equalsIgnoreCase("N")) {
			System.out.println("Y/N? Y for a test, N for the exit.");
			userCommand = sc.nextLine();
			if (userCommand.equalsIgnoreCase("N")) break;
			System.out.println("Arguments (from, to, amount): ");
			convArgs = sc.nextLine().split(" ");
			FinanceCalculator.currencyConversion(convArgs);
		}
		sc.close();
		System.out.println("Test Finished.\n"); // robustness
	}

	/* The driver function. */
	public static void main(String[] args) {

		/* Tests for static variables */
		// test the CRCY static variable in FinanceCalculator
		System.out.println("CRCY: " + CRCY + ".\n");

		/* Tests for Node */

		// test the constructor and the setData methods of the Node
		FinanceCalculator calc = new FinanceCalculator();
		System.out.println("new Node(1): " + calc.genNode(1) + "\n");

		// test the getData method from the Node
		System.out.println("new Node(1).getData(): " + calc.genNode(1).getData() + "\n");

		// test the getLeft and setLeft method for the Node
		System.out.println("new Node(1).getLeft() (left==new Node(2)): " + calc.genChildren(1,2,"L") + "\n");

		// test the getRight and setRight method for the Node
		System.out.println("new Node(1).getRight() (right==new Node(3)): " + calc.genChildren(1,3,"R") + "\n");

		/* Tests for FinanceCalculator */

		// test the constructor of the FinanceCalculator class
		calc = new FinanceCalculator();
		System.out.println("calc:\n" + calc.toString());

		// test the isBalanced static method
		String bracketSet = "(({}[]))";
		System.out.println(FinanceCalculator.isBalanced(bracketSet));

		// test the currencyConversion method
		calc.testCurrencyConversion();

		//	calc.testPaymentPlanning();

		// test the getPriority method
		System.out.println("The priority of * is " + calc.getPriority(MUL) + ".");
		System.out.println("The priority of + is " + calc.getPriority(ADD) + ".");
		System.out.println("The priority of ( is " + calc.getPriority(LTP) + ".\n");
		//System.out.println("The priority of ^ is " + calculator.getPriority("^") + ".\n");

		// test the compareTo method
		System.out.println("The comparison between + and * gives us the difference of priority to be " + calc.compareTo('+','*') + ".");
		System.out.println("The comparison between * and ( gives us the difference of priority to be " + calc.compareTo('*','(') + ".\n");
		//System.out.println("The comparison between ^ and + gives us the difference of priority to be " + calculator.compareTo("*","("));

		// test the (sub) compute method
		double result = calc.compute(1.0,2.0,ADD);
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("1.0 + 2.0 = " + df.format(result));
		result = calc.compute(3.0,4.0,DIV);
		System.out.println("3.0 + 4.0 = " + df.format(result) + "\n");

		// test the getRPN method
		// 1
		String expr = "((3 + (7 * 2)) - (1 / 4)) * (5 - 2)";
		ArrayList<Character> RPNExpression = calc.getRPN(expr);
		System.out.println("RPNExpression: " + RPNExpression.toString() + "\n");
		// 2
		// expr = "3 + 4 * 2 / (1 - 5) ^ 2";
		// System.out.println();
		// calculator.getRPN(expr);
		// System.out.println();
		// set up the String builder
		
		// test the toRealRPN method
		// 1
		String rpn = calc.toRealRPN(RPNExpression);
		System.out.println("rpn: " + rpn + "\n");
		// 2

		// test the evaluate method
		// 1
		Stack<Object> evaluated = calc.evaluate(rpn);
		System.out.println("evaluated: " + evaluated + "\n");
		// 2
		// expr = "(9 + 2) * ((4 - 2) * (5 + 3) + 1) - (7 - 5) * ((6 + 3) * (2 - 1) + 4)";
		// calculator.evaluate(result);
		// System.out.println();
		// 3
		// expr = "((7 - 3) * (9 + 2)) + ((8 / 4) * (6 + 1)) - ((5 * 2) + (1 + 9))";
		// calculator.evaluate(expr);

		// test the DFS method
		// 1
		Node<Object> root = (Node<Object>) evaluated.pop();
		result = calc.DFS(root);
		System.out.println("result: " + df.format(result) + "\n");

		// test the (main) compute method (getRPN + toRealRPN + evaluate + DFS)
		/*
		// 1
		expr = "(6 + 3 * (5 - 2)) / 4";
		System.out.println("expr: " + expr + "\n");
		result = calculator.compute(expr);
		System.out.println("------------------------\n");
		// 2
		expr = "((((((((3) + 1) * 2) - 6) / 5) * 8) - 7) + 4)";
		System.out.println("expr: " + expr + "\n");
		result = calculator.compute(expr);
		System.out.println("------------------------\n");
		// 3
		expr = "(6 +3 *(5- 2))/ 4"; // uneven ver. of 1
		System.out.println("expr: " + expr + "\n");
		result = calculator.compute(expr);
		System.out.println("------------------------");
		// 4
		((3 + (4 * 2)) / (1 - (5 - 6))) * (2 + 3)
		*/
		
		// System.out.println("args: " + Arrays.toString(args));
		// String option = args[0];

	}

}