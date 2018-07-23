package bogurs.algorithm;

import java.util.*;

public class Queue {
	
	private Node front; // 첫 번째 노드를 가리키는 인스턴스
	private Node back; // 마지막 노드를 가리키는 인스턴스
	private int size = 0; // 큐의 size
	
	/**
	 * 노드를 관리하기 위한 내부클래스
	 * @author thsong
	 *
	 */
	public class Node{
		private int data; // 데이터가 저장될 변수
		private Node next; // 다음 노드를 가리키는 변수
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * 정수 data를 큐에 넣는 연산이다.
	 * @param data
	 */
	private void push(int data) {
		Node newNode = new Node(data); //큐에 연결하기 위한 새 노드 객체 생성
		if(size == 0) { // 만약 아직 데이터가 한 개도 없다면..
			newNode.next = front; // 새로운 노드의 다음 노드로 헤드를 지정
			front = newNode; // 헤드라는 포인터 객체에 새로 생성한 노드를 지정
			size++;
			if(front.next == null) {
				back = front; //만약, 헤드 포인터 객체의 다음에 지정한 노드가 없다면 꼬리 포인터 객체에 헤드와 같은 주소를 지정함
			}
		} else { //만약 데이터가 1개라도 있다면..
			back.next = newNode;  //꼬리 포인터 객체가 지정한 다음 노드로 새 노드를 지정한다.
			back = newNode; // 꼬리 포인터 객체에 새 노드를 지정한다.
			size++;
		}
	}

	/**
	 * 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void pop() {
		if(front == null) {
			System.out.println(-1);
		} else {
			Node temp = front;
			front = temp.next;
			if(front == null) {
				back = null;
			}
			System.out.println(temp.data);
			temp = null;
			size--;
		}
	}

	/**
	 * 큐에 들어있는 정수의 개수를 출력한다.
	 */
	private void size() {
		System.out.println(this.size);
	}
	
	/**
	 * 큐가 비어있으면 1, 아니면 0을 출력한다.
	 */
	private void isEmpty() {
		if(size == 0) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	/**
	 * 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void front() {
		if(front == null) {
			System.out.println(-1);
		} else {
			System.out.println(front.data);
		}
	}

	/**
	 * 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void back() {
		if(back == null) {
			System.out.println(-1);
		} else {
			System.out.println(back.data);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // Run Size
		
		Queue q = new Queue(); // Queue Init
		for(int i = 0; i <= t; i++) {
			String tempLine = sc.nextLine();
			String[] compLine = tempLine.split(" ");
			
			if("push".equals(compLine[0])) {
				q.push(Integer.parseInt(compLine[1]));
			} else if ("pop".equals(compLine[0])) {
				q.pop();
			} else if ("size".equals(compLine[0])) {
				q.size();
			} else if ("empty".equals(compLine[0])) {
				q.isEmpty();
			} else if ("front".equals(compLine[0])) {
				q.front();
			} else if ("back".equals(compLine[0])) {
				q.back();
			}
		}
		
		sc.close();
		
//		q.push(1); // push Integer
//		q.push(2); // push Integer
//		q.push(3); // push Integer
//		q.push(4); // push Integer
//		q.size();
//		q.isEmpty(); // print if Queue is empty
//		q.front();
//		q.back();
//		q.pop(); // pop Integer
//		q.pop(); // pop Integer
//		q.pop(); // pop Integer
//		q.pop(); // pop Integer
//		q.pop(); // pop Integer
//		q.front();
//		q.back();
//		q.isEmpty(); // print if Queue is empty
	}

}
