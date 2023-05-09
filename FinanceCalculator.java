import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Hashtable;

/*
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
*/

import java.io.IOException;
import java.util.EmptyStackException;

// https://www.fncalculator.com/currencyConverter
// https://microsoft.github.io/react-native-windows/docs/rnm-getting-started

/*
The implementation of a to take calculations including:
- addition
- subtraction
- multiplication
- division
- power
- *boolean-based operation (AND, OR, XOR, etc.)
- *differentiation
- *integration
- cash flow analysis
- regression analysis
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

	    public Node() {
	    	this.data = null;
	    	this.left = null;
	    	this.right = null;
	    }

	    public Node(T data) {
	        //Assign data to the new node, set left and right children to null
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
			contents += " " + data.toString() + " "; // add the contents of the root node
			contents += right != null ? right.toString() : ""; // add the contents of the right node
			contents += RTP;
			return contents;
		}

		/*
		public static void main(String []args) {
			Node<Integer> testNode = new Node<>(1);
			testNode.setLeft(new Node(2));
			testNode.setRight(new Node(3));
			System.out.println(testNode.toString());
		}
		*/

	}

	/* The space for static variables. */
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
		System.out.println("The first character " + String.valueOf(op1) + " comes to be with a priority of " + Integer.toString(pValue1) + ".");
		int pValue2 = getPriority(op2);
		System.out.println("The second character " + String.valueOf(op2) + " comes to be with a priority of " + Integer.toString(pValue2) + ".");
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
			System.out.println("We are adding a and b: " + a + "+" + b + ".");
			result = a + b;
			break;
		case SUB:
			System.out.println("We are subtracting a from b: " + a + "-" + b + ".");
			result = a - b;
			break;
		case MUL:
			System.out.println("We are multiplying a and b: " + a + "*" + b + ".");
			result = a * b;
			break;
		case DIV:
			System.out.println("We are dividng a by b: " + a + "/" + b + ".");
			result = a / b;
			break;
		}
		return result;
	}

	/*
	Test if the expression is a valid one. By means of validity,
	the open-parenthesis and the closing one must have the right
	order and a correct count of matching points.
	*/
	private boolean isValid(String expression) {
		// TODO: catch the EmptyStackException
		return true;
	}

	/*
	Convert an arithmetic expression into Reverse-Polish Notation.
	*/
	private ArrayList<Character> getRPN(String expression) {
		// 创建一个结果列表和一个操作符堆栈。
		Stack<Character> operators = new Stack<>();
		ArrayList<Character> results = new ArrayList<>();
		int index;
		char current = ' ';
		int len = expression.length();
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
				System.out.println("The current character is a number, being " + current + ".");
				results.add(current);
				break;
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				//如果它是一个操作符，比较其与操作符堆栈顶部的操作符的优先级。
				//如果操作符堆栈顶部的操作符优先级高于或等于该操作符，则弹出堆栈中的操作符，将它们输出到结果列表中。
				System.out.println("The current character is an operator, being " + current + ".");
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
					System.out.println("The current character is not as prioritized as the operators do.");
				}
				//然后将该操作符压入堆栈中。
				operators.push(current);
				//重复此步骤直到操作符堆栈为空或堆栈顶部的操作符优先级低于该操作符。
				break;
			case LTP:
				//如果它是一个左括号，则将其压入堆栈中。
				System.out.println("LTP recognized.");
				operators.push(current);
				System.out.println("LTP stored.");
				break;
			case RTP:
				//如果它是一个右括号，则弹出堆栈中的操作符，并将它们输出到结果列表中，直到遇到左括号为止。
				System.out.println("RTP recognized.");
				char currOp = operators.peek();
				while (currOp != LTP) {
					results.add(operators.pop());
					currOp = operators.peek();
				}
				if (currOp == LTP) {
					operators.pop(); //左括号只弹出，不输出到结果列表中。
					System.out.println("LTP popped.");
				}
				break;
			case SP:
				System.out.println("The current character is a space.");
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
			System.out.println("The current notation is: " + currentNotation + ".");
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
				System.out.println("A node of a BST is created: " + newNode.toString());
				System.out.println("We push the number " + currentNotation + " into the number stack.");
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
				System.out.println("We set up a new node whose data is the operator, with children being the two popped nodes.");
				//然后将新创建的节点压入栈中。
				operators.push(opNode); // Stack<Node>? change the data type
				System.out.println("We push the operator " + currentNotation + " into the operator stack.");
				break;
			case SP:
				System.out.println("The current entry is a space, and we have skipped this.\n");
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
	Compute 
	*/
	public double compute(String expression) {
		ArrayList<Character> RPNExpression = this.getRPN(expression);
		String rpn = this.toRealRPN(RPNExpression);
		Stack<Object> tree = this.evaluate(rpn);
		Node<Object> evaluated = (Node<Object>) tree.pop();
		return DFS(evaluated);
	}

	/*
	Convert a given amount of money from a type
	of currency to another. The rate is based
	with in-time gleaning of data on the website.
	*/
	public static double currencyConversion(String from, String to, double amount) {
		// glean the current currency for a set of currency
		String output = " ";
		double fromRate = CRCY.get(from);
		double toRate = CRCY.get(to);
		double total = fromRate / toRate * amount;
		System.out.println("We are converting " + amount + " " + from + " to " + to + ".\nThis gives us a total of " + total + ".\n");
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
		//2
		String baseInfo = "";
		return "";
	}

	/*
	Trim the original expression so that there is no space
	between any of the operators within the expression.
	*/
	private String trim(String expression) {
		String trimmed = "";
		return "";
	}

	/* The driver function. */
	public static void main(String[] args) {

		// test the static variable
		System.out.println("CRCY: " + CRCY + ".\n");

		// test the currencyConversion method
		FinanceCalculator.currencyConversion("USD","INR",100);

		// test the constructor
		FinanceCalculator calculator = new FinanceCalculator();

		// test the getRPN method
		// ArrayList<Character> rpn = calculator.getRPN(expr);
		// System.out.println();
		// expr = "3 + 4 * 2 / (1 - 5) ^ 2";
		// calculator.getRPN(expr);
		// System.out.println();
		// set up the String builder
		
		// test the toRealRPN method

		// test the evaluate method
		// expr = "(9 + 2) * ((4 - 2) * (5 + 3) + 1) - (7 - 5) * ((6 + 3) * (2 - 1) + 4)";
		// calculator.evaluate(result);
		//System.out.println();
		//expr = "((7 - 3) * (9 + 2)) + ((8 / 4) * (6 + 1)) - ((5 * 2) + (1 + 9))";
		//calculator.evaluate(expr);

		// test the (main) compute method
		String expr = "(6 + 3 * (5 - 2)) / 4";
		double result = calculator.compute(expr);
		System.out.println("result: " + result);

	}

}