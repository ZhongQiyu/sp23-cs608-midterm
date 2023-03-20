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
	​ 3.1、如果当前符号栈为空，就直接入栈
	​ 3.2、如果符号栈有运算符，就需要进行比较
	​ 如果当前运算符的优先级小于或等于栈中的运算符，就需要从数栈中pop出两个数，在符号栈中pop出一个符号，进行运算，得到结果，入数栈，然后将当前的操作符入符号栈
	​ 如果当前运算符的优先级大于栈中的运算符，就直接入符号栈
	4、当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并进行计算
	5、最后保留在数栈中的那个数字就是运算的结果
	*/
}