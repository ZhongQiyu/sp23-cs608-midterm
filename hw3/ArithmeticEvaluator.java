import java.util.Stack;
import java.util.ArrayList;
// import hw3.GenericStack;

public class ArithmeticEvaluator { // add generic
	
	private static final String ADD = "+";
	private static final String SUB = "-";
	private static final String MUL = "*";
	private static final String DIV = "/";
	private static final String LTP = "(";
	private static final String RTP = ")";
	private static final String SP = " ";
	private static final int HIGH = 3;
	private static final int MEDIUM = 2;
	private static final int LOW = 1;
	private Stack<String> numbers;
	private Stack<String> operators;

	private Stack<String> getNumbers() { return numbers; }

	private Stack<String> getOperators() { return numbers; }

	public ArithmeticEvaluator() {
		numbers = new Stack<>();
		operators = new Stack<>();
	}

	public ArithmeticEvaluator(String expr) {
		// call distribute() to initialize numbers and operators
	}

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

	private double compute(double a, double b, String op) {
		double result = 0.0;
		switch (op) {
		case ADD:
			result = a + b;
		case SUB:
			result = a - b;
		case MUL:
			result = a * b;
		case DIV:
			result = a / b;
		}
		return result;
	}

	private void distribute(String expression) {
		Stack<String> numbers = this.getNumbers();
		Stack<String> operators = this.getOperators();
		//private static final String 
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
	Test if the expression is a valid one. By means of validity,
	the open-parenthesis and the closing one must have the right
	order and a correct count of matching points.
	*/
	private boolean isValid(String expression) {
		return true;
	}

	/*
	Evaluate the results given a expression that contains numbers
	and operators. The expression might be invalid, and we only
	compute the results for the valid ones. The data types supported
	for the moment is mainly Integer (int).
	*/
	public String evaluate(String expression) {
		// call isValid() with if-else then pass in the numbers
		Stack<String> numbers = this.getNumbers();
		Stack<String> operators = this.getOperators();
		ArrayList<String> subResults = new ArrayList<>();  // the space to store the results of the partial numbers
		int count = expression.length();
		double result = 0.0;
		String currentNotation = ""; // generically store every single item to be passed in
		int currentPriority = 0; // for distinguishing the order of using different computational operators (+, -, *, /)
		int index;
		for (index = 0; index < count; index++) {
			currentNotation = String.valueOf(expression.charAt(index));
			switch (currentNotation) {
			case LTP:
			case ADD:
			case SUB:
			case MUL:
			case DIV:
				System.out.println("We push " + currentNotation + " into the operator stack.");
				operators.push(currentNotation);
				break;
			case RTP:
				System.out.println("We push the closing parenthesis into the operator stack.");
				operators.push(RTP);
				System.out.println("We start to pop the elements in the operator stack until the parenthesis matches.");
				boolean matched = false;
				double result = 0.0;
				String popped = "";
				while (!matched) {
					if (popped.equals(LTP)) matched = true;
					else popped = operators.pop();
					System.out.println("popped: " + popped);
				}
				subResults.push(result);
				break;
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
				numbers.push(currentNotation); // call Integer.valueOf() later in the computation operations
				break;
			case SP: continue;
			}

			/*
			// TODO: catch the EmptyStackException
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
		return "";
	}

	/*
	Display the information that is stored in the evaluator.
	*/
	public String toString() {
		//2
		return "";
	}

	public static void main(String[] args) {
		// test the constructor
		ArithmeticEvaluator evaluator = new ArithmeticEvaluator();

		// test the evaluate method
		String expr = "(9 + 2) * ((4 - 2) * (5 + 3) + 1) - (7 - 5) * ((6 + 3) * (2 - 1) + 4)";
		evaluator.evaluate(expr);
	}

}