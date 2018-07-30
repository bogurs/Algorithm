package bogurs.algorithm;

class MyStack<T> {
	@SuppressWarnings("hiding")
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data){
			this.data = data;
		}
	}
	
	private Node<T> top;

	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		Node<T> tempNode = top;
		top = newNode;
		newNode.next = tempNode;
	}

	public void pop() {
		if (top == null) {
			throw new NullPointerException("[ERROR]데이터가 없습니다. 먼저 데이터를 삽입(push) 해주세요!!");
		} else {
			Node<T> tempNode = top;
			top = tempNode.next;
			System.out.println(tempNode.data);
			tempNode = null;
		}
	}

	public void peek() {
		if (top == null) {
			throw new NullPointerException("[ERROR]데이터가 없습니다. 먼저 데이터를 삽입(push) 해주세요!!");
		} else {
			System.out.println(top.data);
		}
	}

	public void isEmpty() {
		System.out.println(top == null);
	}
	
}

public class Stack_withNode {
	public static void main(String[] args) {
		MyStack<Integer> ms = new MyStack<>();
		ms.push(1);
		ms.push(2);
		ms.push(3);
		ms.isEmpty();
		ms.peek();
		ms.pop();
		ms.push(4);
		ms.peek();
		ms.pop();
		ms.peek();
		ms.pop();
		ms.push(5);
		ms.pop();
		ms.pop();
		ms.isEmpty();
		
//		MyStack<String> mss = new MyStack<>();
//		mss.push("Song.");
//		mss.push("Tae Hyun ");
//		mss.push("My name is ");
//		mss.isEmpty();
//		mss.peek();
//		mss.pop();
//		mss.peek();
//		mss.pop();
//		mss.peek();
//		mss.pop();
//		mss.isEmpty();
	}
}
