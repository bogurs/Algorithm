package bogurs.algorithm;

class MyQueue<T> {
	@SuppressWarnings("hiding")
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data){
			this.data = data;
		}
	}
	
	private Node<T> top;
	private Node<T> rear;

	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		if (rear == null) {
			rear = newNode;
			top = newNode;
		} else {
			if (rear == top) {
				rear.next = newNode;
			} else {
				top.next = newNode;
			}
			top = newNode;
		}
	}

	public void pop() {
		if (rear == null) {
			throw new NullPointerException("[ERROR]데이터가 없습니다. 먼저 데이터를 삽입(push) 해주세요!!");
		} else {
			Node<T> tempNode = rear;
			rear = tempNode.next;
			System.out.println(tempNode.data);
			tempNode = null;
		}
	}

	public void peek() {
		if (rear == null) {
			throw new NullPointerException("[ERROR]데이터가 없습니다. 먼저 데이터를 삽입(push) 해주세요!!");
		} else {
			System.out.println(rear.data);
		}
	}

	public void isEmpty() {
		System.out.println(rear == null);
	}
	
}

public class Queue_withNode {
	public static void main(String[] args) {
		MyQueue<Integer> ms = new MyQueue<>();
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
		
		MyQueue<String> mss = new MyQueue<>();
		mss.push("Song.");
		mss.push("Tae Hyun ");
		mss.push("My name is ");
		mss.isEmpty();
		mss.peek();
		mss.pop();
		mss.peek();
		mss.pop();
		mss.peek();
		mss.pop();
		mss.isEmpty();
	}
}
