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
	public String evaluate(String expression) {
		Stack<Integer> numbers = this.getNumbers();
		Stack<String> operators = this.getOperators();
		ArrayList<String> temp = new ArrayList<>(); // to store the numbers and the operators involved in the current pair of parentheses
		int count = expression.length();
		ArrayList<Integer> subResults = new ArrayList<>();
		String currentNotation = ""; // generically store every single item to be passed in
		int currentPriority = 0; // for distinguishing the order of using different computational operators (+, -, *, /)
		int index;
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
				int pASCII = 0;
				System.out.println("numbers: " + numbers.toString());
				System.out.println("operators: " + operators.toString());
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

}