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

	private Stack<Integer> getNumbers() { return numbers; }

	private Stack<String> getOperators() { return operators; }

	private void setNumbers(Stack<Integer> numbers) { this.numbers = numbers; }

	private void setOperators(Stack<String> operators) { this.operators = operators; }

	public ArithmeticEvaluator() {
		numbers = new Stack<>();
		operators = new Stack<>();
	}


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
		/*
		currentPriority = getPriority(index);
		if (currentPriority == HIGH) {
			if (currentNotation.equals(LTP) { continue; } // keep reading while donno if still need this
			else { 
				double a = 0.0;
				double b = 0.0;
				double result = 0.0;
				String op = "";
				boolean parsed = false;
				String localExpr = "";
				while (!parsed) {
					if ((localExpr.contains(LTP)) && (localExpr.contains(RTP))) { parsed = true; }
					else {
						a = 
						localExpr.insert(operators.pop(0), 0);
					}
				}
				result = compute(result, compute())
				} // currentNotation.equals(RTP))
		else {
			if (currentNotation.equals(MUL) || currentNotation.equals(DIV))
			op = operators.pop();
			a = numbers.pop();
			b = numbers.pop();
			if (op.equals(RTP)) { 
				op = current
				result = numbers; }
			else { result = compute(a, b, op); }
		}
		*/
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
		ArrayList<String> tempOps = new ArrayList<>(); // to store the popped operators; may be able to get simplified
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
				String popped = currentNotation; // in this case, RTP also works
				int pASCII = 0;
				/*
				System.out.println("numbers: " + numbers.toString());
				System.out.println("operators: " + operators.toString());
				*/
				while (!matched) {
					pASCII = (int)popped.charAt(0);
					System.out.println("pASCII: " + pASCII);
					if (popped.equals(LTP)) { // the current pair of brackets is done with calculation
						matched = true;
						System.out.println("The parentheses now matched.");
					}
					else { // the current pair of brackets is under calculation
						if ((pASCII >= 48) && (pASCII <= 57)) { // being a number
							popped = Integer.toString(numbers.pop());
							System.out.println("number popped: " + popped);
						}
						else { // being an operator
							popped = operators.pop();
							System.out.println("operator popped: " + popped);
						}
						if ((pASCII < 40) || (pASCII > 41)) tempOps.add(popped); // include the real operators
					}	
				}
				System.out.println("numbers: " + numbers.toString());
				System.out.println("operators: " + operators.toString());
				System.out.println("temp: " + temp.toString());
				/*
				int first = 0;
				int second = 0;
				ArrayList<Integer> tempNums = new ArrayList<>();
				String currentOp = tempOps.get(0); // guaranteed that there is at least 1 operator
				if ((pASCII < 40) || (pASCII > 41)) { // being a non-parenthesis operator
					System.out.println("We push the notation to the tempOps list for calculation.");
					tempOps.add(popped);
				}
				else System.out.println("The notation is either an open parenthesis or a closing one."); // being a parenthesis
				if (matched) {
					System.out.println("pASCII: " + pASCII);
				}
				*/
				/*
				int first = numbers.peek(); // get the first number in the number stack
				int second = numbers.peek(); // initialize the second number in the number stack
				while (!tempOps.isEmpty()) {
					subResult = compute(second, first, currentOp);
					System.out.println("subResult: " + subResult);
					subResults.add((int)subResult);
					System.out.println("subResults: " + subResults.toString());
					tempOps.remove(0);
					System.out.println("numbers: " + numbers.toString());
					System.out.println("tempOps: " + tempOps.toString());
					System.out.println("\n");
				}
				*/
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