import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;

/*
An evaluator to take calculations including:
- addition
- subtraction
- multiplication
- division
- *differentiation
- *integration
- cash flow analysis
- regression analysis
- etc.
The final result would be displayed in the screen.
* means that the function is under implementation.
*/
public class ArithmeticEvaluator { // add generic
	
	private static final String ADD = "+";
	private static final String SUB = "-";
	private static final String MUL = "*";
	private static final String DIV = "/";
	private static final String LTP = "(";
	private static final String RTP = ")";
	private static final String SP = " ";
	//private static final String 
	private static final ArrayList<String> NUMS = new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
	private static final int HIGH = 3;
	private static final int MEDIUM = 2;
	private static final int LOW = 1;
	private Stack<Integer> numbers;
	private Stack<String> operators;

	/* Get the stack of numbers	stored in the evaluator. */
	private Stack<Integer> getNumbers() { return numbers; }

	/* Get the stack of operators stored in the evaluator. */
	private Stack<String> getOperators() { return operators; }

	/* Set the stack of numbers with a new one. */
	private void setNumbers(Stack<Integer> numbers) { this.numbers = numbers; }

	/* Set the stack of operators with a new one. */
	private void setOperators(Stack<String> operators) { this.operators = operators; }

	/* Default constructor of an evaluator. */
	public ArithmeticEvaluator() {
		numbers = new Stack<>();
		operators = new Stack<>();
	}

	/* Non-default constructor of an evaluator. */
	public ArithmeticEvaluator(String expr) {
		// call distribute() to initialize numbers and operators
	}

	/*
	Compute the priority of different operators.
	*/	
	private int getPriority(String op) {
		int priority = 0;
		switch (op) {
		case LTP:
		case RTP:
			priority = HIGH;
			break;
		case MUL:
		case DIV:
			priority = MEDIUM;
		case ADD:
		case SUB:
			priority = LOW;

		}
		return priority;
	}

	/*
	Compare the Strings together so that the priorities would
	be computed.
	*/
	private int compareTo(String op1, String op2) {
		int pValue1 = getPriority(op1);
		int pValue2 = getPriority(op2);
		return pValue1 <= pValue2 ? -1:1;
	}

	/*
	Compute the result of a given type of operation and
	two numbers involved in the operations.
	*/
	private double compute(double a, double b, String op) {
		double result = 0.0;
		switch (op) {
		case ADD:
			System.out.println("We are adding a and b:");
			result = a + b;
			break;
		case SUB:
			System.out.println("We are subtracting a from b:");
			result = a - b;
			break;
		case MUL:
			System.out.println("We are multiplying a and b:");
			result = a * b;
			break;
		case DIV:
			System.out.println("We are dividng a by b:");
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
	Evaluate the results given a expression that contains numbers
	and operators. The expression might be invalid, and we only
	compute the results for the valid ones. The data types supported
	for the moment is mainly Integer (int).
	*/
	public String evaluate(String expression) { // change the return type to be at least double
		Stack<Integer> numbers = this.getNumbers();
		Stack<String> operators = this.getOperators();
		ArrayList<String> temp = new ArrayList<>(); // to store the numbers and the operators involved in the current pair of parentheses
		int count = expression.length();
		ArrayList<Integer> subResults = new ArrayList<>();
		String currentNotation = ""; // generically store every single item to be passed in
		int currentPriority = 0; // for distinguishing the order of using different computational operators (+, -, *, /)
		int index;
		// Omega(n^2)
		for (index = 0; index < count; index++) {
			currentNotation = String.valueOf(expression.charAt(index));
			if (!currentNotation.equals(SP)) temp.add(currentNotation);
			switch (currentNotation) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				System.out.println("We push the number " + currentNotation + " into the number stack.");
				numbers.push(Integer.parseInt(currentNotation)); // call Integer.valueOf() later in the computation operations
				break;
			case LTP:
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				System.out.println("We push " + currentNotation + " into the operator stack.");
				operators.push(currentNotation);
				break;
			case RTP:
				System.out.println("We push the closing parenthesis into the operator stack, popping the elements until the parentheses match.");
				operators.push(RTP);
				boolean matched = false;
				double subResult = 0.0;
				String popped = ""; // in this case, RTP also works
				String current = "";
				int pASCII = 0;
				System.out.println("numbers: " + numbers.toString());
				System.out.println("operators: " + operators.toString());
				int exprLen = temp.size();
				int exprIndex;
				System.out.println("temp: " + temp.toString());
				for (exprIndex = 0; exprIndex < exprLen; exprIndex++) { //while ((!numbers.isEmpty() || (!operators.isEmpty()))
					pASCII = (int)(temp.get(exprLen - exprIndex - 1).charAt(0));
					System.out.println("pASCII: " + pASCII);
					/*
					if (popped.equals(LTP)) { // the current pair of brackets is done with calculation
						matched = true;
						System.out.println("The parentheses now matched.");
					}
					else { // the current pair of brackets is under calculation
						popped = temp.remove(temp.size()-1);
						System.out.println(popped);
						if ((pASCII >= 48) && (pASCII <= 57)) { // being a number
							System.out.println("number popped: " + popped);
						}
						else { // being an operator
							System.out.println("operator popped: " + popped);
						}
					}
					System.out.println("numbers.top: " + Integer.toString(numbers.peek()));
					System.out.println("operators.top: " + operators.peek());
					*/
				}
				// while the current local expression is not yet paired
				// peek the operator stack's first element
				// - if it is a RTP, then pop it and also pop the first element in the number stack
				// - otherwise just pop the first element in the number stack (the first number)
				// pop the first element (previously the second element) in the number stack and the operator stack
				// - the first element in the number stack should be the second number
				// - the first element in the operator stack should be the true operator
				// assume that the expression is valid, we would have gained a sub-result
				break;
			case SP:
				//System.out.println("The current entry is a space, and we have skipped this.");
				continue;
			}
		}
		return "";
	}

	/*
	Display the information that is stored in the evaluator.
	*/
	public String toString() {
		//2

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

	/*
	Distribute a given expression into numbers and operators.
	*/
	private void distribute(String expression) {
		Stack<Integer> numbers = this.getNumbers();
		Stack<String> operators = this.getOperators();
	}

	/* The driver function. */
	public static void main(String[] args) {
		// test the constructor
		ArithmeticEvaluator evaluator = new ArithmeticEvaluator();

		// test the evaluate method
		String expr = "(9 + 2) * ((4 - 2) * (5 + 3) + 1) - (7 - 5) * ((6 + 3) * (2 - 1) + 4)";
		evaluator.evaluate(expr);
	}

	/*
	当我们输入一个类似于“7*2+100-5+3-4/2”的简单中缀表达式时，我们的编译器能够利用我们所编写的代码将这个表达式扫描并计算出其结果
	在这个表达式中主要有两种元素，一种是数字，一种是符号，那么我们就需要创建两个栈结构存储数据
	数栈numStack：存放数
	符号栈operStack：存放运算符
	1、首先我们需要定义一个index（索引），来遍历我们的表达式
	2、如果扫描到一个数字，就直接入数栈
	3、如果扫描到一个运算符，那就要分以下几种情况：
		3.1、如果当前符号栈为空，就直接入栈
		3.2、如果符号栈有运算符，就需要进行比较
		3.3、如果当前运算符的优先级小于或等于栈中的运算符，就需要从数栈中pop出两个数，在符号栈中pop出一个符号，进行运算，得到结果，入数栈，然后将当前的操作符入符号栈
		3.4、如果当前运算符的优先级大于栈中的运算符，就直接入符号栈
	4、当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并进行计算
	5、最后保留在数栈中的那个数字就是运算的结果
	*/

}