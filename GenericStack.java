import java.util.ArrayList;
import java.lang.reflect.Field;

public class Stack<T> {
	
	private ArrayList<T> values;

	public Stack() {
		values = null;
	}

	public Stack(ArrayList<T> values) {
		this.values = values;
	}

	private int compareTo(T a, T b) { 
		
	}

    public static class Foo<T> {
        public T data;
		/*
		switch () {
		case 
		}
		*/
	}

	public int pop() {

	}

	public int push() {

	}

	public Stack() {

	}

	public static void main(String[] args) throws NoSuchFieldException {
        Foo<String> foo = new Foo<String>();
        Class fooClass = foo.getClass();
        Field field = fooClass.getField("data");
    
        System.out.println(fooClass);
        System.out.println(field.getType());
        System.out.println(field.getGenericType());
        /* public Stack() */
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

	/*
	ArrayList<String> tempOps = new ArrayList<>(); // to store the popped operators; may be able to get simplified
	if ((pASCII < 40) || (pASCII > 41)) tempOps.add(popped); // include the real operators
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
}

"""
Midterm projects are due at March 21 
You will be presenting on March 21 your group projects.

https://docs.google.com/spreadsheets/d/1ZTNmO6BNI7xEpw0eh7F92M37m_ONOh1iEtd4p28RNKo/edit?usp=sharing

Project Presentation Guidelines
Collaboration is a creative process that brings ideas to life through the development of software, hardware, or projects.
In this task, you will design and implement a project that uses algorithms and might solve a problem, enable innovation,
explore personal interests, or express creativity. Your submission must include the elements listed in the submission requirements section below.
You are allowed to collaborate with your partner(s) on the development of the project, the written response f(or the final only),
and the video that you submit for this project can be completed collaboratively or individually, with or without any collaboration with your partner(s).

General Requirements 
You will complete and submit the following: 
• Final program code that runs (created independently or collaboratively) 
• A video that displays the running of your program and demonstrates functionality you developed 
• Written paper
• Ppt presentation 
 
Submission Requirements 
1. PROGRAM CODE (CREATED INDEPENDENTLY OR COLLABORATIVELY) Submit a folder in zip format or one PDF file that contains all of your program code (including comments).
Include comments or acknowledgments for any part of the submitted program code that has been written by someone other than you and/or your collaborative partner(s).
The program must run.

2. VIDEDO
Submit one video in .mp4, .wmv, .avi, or .mov format that demonstrates the running of at least the significant features of your program.
Your video must not exceed 5 minutes in length.
Provide an audio narration in your video that:
• identifies the programming language;
• identifies the purpose and the functionality of your program/project; and
• explains what the video illustrates.

3. WRITTEN PAPER for final project only not for midterm
You will write a paper of no more than 2 single-column, 1.5-spaced, 12pt font pages.
Provide information on the project and on your presentation. Name the project that is represented by your presentation.
Describe the projects intended purpose and functionality. Describe how your presentation illustrates, represents, or
explains the project’s intended purpose, its function, or its effect.
Describe your development process, explicitly identifying the computing tools and techniques you used to create your presentation.
Your description must be detailed enough so that a person unfamiliar with those tools and techniques will understand your process. 
Explain at least one beneficial effect and at least one harmful effect the project has had, or has the potential to have, on society,
economy, or culture.
Using specific details, describe:
- the data your project uses;
- how the project consumes (as input), produces (as output),
- and/or transforms data; and
- at least one data storage concern, data privacy concern, or data security concern directly related to the project.

4) POWERPOINT PRESENTATION
References: Provide a list of at least three online or print sources used to create your presentation.
"""

// testing