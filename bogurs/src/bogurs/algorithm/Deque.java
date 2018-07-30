package bogurs.algorithm;

import java.util.*;

public class Deque {
	
	private Node front; // 첫 번째 노드를 가리키는 인스턴스
	private Node back; // 마지막 노드를 가리키는 인스턴스
	private int size = 0; // 덱의 size
	
	/**
	 * 노드를 관리하기 위한 내부클래스
	 * @author thsong
	 *
	 */
	public class Node{
		private int data; // 데이터가 저장될 변수
		private Node next; // 다음 노드를 가리키는 변수
		private Node prev; // 이전 노드를 가리키는 변수
		
		public Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	/**
	 * 정수 data를 덱의 앞에 넣는 연산이다.
	 * @param data
	 */
	private void push_front(int data) {
		Node newNode = new Node(data); //덱에 연결하기 위한 새 노드 객체 생성
		if(front != null) {
			newNode.next = front; // 새로운 노드의 다음 노드로 헤드를 지정
			front.prev = newNode;
		}
		front = newNode; // 헤드라는 포인터 객체에 새로 생성한 노드를 지정
		size++;
		if(front.next == null) {
			back = front; //만약, 헤드 포인터 객체의 다음에 지정한 노드가 없다면 꼬리 포인터 객체에 헤드와 같은 주소를 지정함
		}
	}
	
	/**
	 * 정수 data를 덱의 뒤에 넣는 연산이다.
	 * @param data
	 */
	private void push_back(int data) {
		Node newNode = new Node(data); //덱에 연결하기 위한 새 노드 객체 생성
		if(size == 0) { // 만약 아직 데이터가 한 개도 없다면..
			push_front(data);
		} else { //만약 데이터가 1개라도 있다면..
			back.next = newNode;  //꼬리 포인터 객체가 지정한 다음 노드로 새 노드를 지정한다.
			newNode.prev = back;
			back = newNode; // 꼬리 포인터 객체에 새 노드를 지정한다.
			size++;
		}
	}

	/**
	 * 덱에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void pop_front() {
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
			if(size == 1) {
				back.prev = null;
			}
		}
	}
	
	/**
	 * 덱에서 가장 뒤에 있는 정수를 빼고, 그 수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void pop_back() {
		if(back == null) {
			System.out.println(-1);
		} else {
			Node temp = back;
			back = temp.prev;
			if(back == null) {
				front = null;
			}
			System.out.println(temp.data);
			temp = null;
			size--;
			if(size == 1) {
				front.next = null;
			}
		}
	}

	/**
	 * 덱에 들어있는 정수의 개수를 출력한다.
	 */
	private void size() {
		System.out.println(this.size);
	}
	
	/**
	 * 덱가 비어있으면 1, 아니면 0을 출력한다.
	 */
	private void isEmpty() {
		if(size == 0) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	/**
	 * 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void front() {
		if(front == null) {
			System.out.println(-1);
		} else {
			System.out.println(front.data);
		}
	}

	/**
	 * 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	 */
	private void back() {
		if(back == null) {
			System.out.println(-1);
		} else {
			System.out.println(back.data);
		}
	}
	
	public static void main(String[] args) {
//		push_front X: 정수 X를 덱의 앞에 넣는다.
//		push_back X: 정수 X를 덱의 뒤에 넣는다.
//		pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//		pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//		size: 덱에 들어있는 정수의 개수를 출력한다.
//		empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
//		front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//		back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // Run Size
		
		Deque dq = new Deque(); // Deque Init
		for(int i = 0; i <= t; i++) {
			String tempLine = sc.nextLine();
			String[] compLine = tempLine.split(" ");
			
			if("push_front".equals(compLine[0])) {
				dq.push_front(Integer.parseInt(compLine[1]));
			} else if("push_back".equals(compLine[0])) {
				dq.push_back(Integer.parseInt(compLine[1]));
			} else if ("pop_front".equals(compLine[0])) {
				dq.pop_front();
			} else if ("pop_back".equals(compLine[0])) {
				dq.pop_back();
			} else if ("size".equals(compLine[0])) {
				dq.size();
			} else if ("empty".equals(compLine[0])) {
				dq.isEmpty();
			} else if ("front".equals(compLine[0])) {
				dq.front();
			} else if ("back".equals(compLine[0])) {
				dq.back();
			}
		}
		
		sc.close();
		
	}

}
