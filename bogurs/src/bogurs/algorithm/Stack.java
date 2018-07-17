package bogurs.algorithm;

import java.util.Scanner;

public class Stack {
	
	int[] stackArr; // Stack Array Matrix
	int top = 0; // index
	
	public Stack(int size) {
		stackArr = new int[size];
	}

	private void push(int i) {
		if(top < stackArr.length) {
			stackArr[top++] = i;
		} else {
			System.out.println("Stack Overflow Error!!");
		}
	}
	
	private void pop() {
		if(top > 0) {
			System.out.println(stackArr[--top]);
		} else {
			System.out.println("Stack Underflow Error!!");
		}
	}
	
	private void peek() {
		if(top > 0) {
			System.out.println(stackArr[top-1]);
		} else {
			System.out.println("No data Error!!");
		}
	}
	
	private void isEmpty() {
		System.out.println(top == 0);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt(); // Stack Size
		
		Stack s = new Stack(size); // Stack Init
		
		s.push(1); // push Integer
		s.push(2); // push Integer
		s.push(3); // push Integer
		s.push(4); // push Integer
		s.isEmpty(); // print if Stack is empty
		s.peek();
		s.pop(); // pop Integer
		s.pop(); // pop Integer
		s.pop(); // pop Integer
		s.pop(); // pop Integer
		s.pop(); // pop Integer
		s.peek(); // print top Integer
		s.isEmpty(); // print if Stack is empty
		
		sc.close();
	}

}
