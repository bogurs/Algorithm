package bogurs.algorithm;

import java.util.LinkedList;

public class HashTable {
	
	/**
	 * key와 value를 저장하는 Node 클래스 정의
	 * @author thsong
	 *
	 */
	class Node { 
		String key;
		String value;
		public Node(String key, String value) {
			this.key = key;
			this.value = value;
		}
		String value() {
			return value;
		}
		void value(String value) {
			this.value = value;
		}
	}
	
	LinkedList<Node>[] data; // Hash Table 방
	
	/**
	 * Hash Table 방 size에 따른 LinkedList 초기화(생성자)
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		this.data = new LinkedList[size];
	}
	
	/**
	 * 주어진 key값에 대한 hashcode를 계산하여 반환
	 * @param key
	 * @return
	 */
	int getHashCode(String key) {
		int hashcode = 0;
		for(char c : key.toCharArray()) {
			hashcode += c;
		}
		return hashcode;
	}
	
	/**
	 * 주어진 hashcode값에 대한 Index를 계산하여 반환
	 * @param hashcode
	 * @return
	 */
	int convertToIndex(int hashcode) {
		return hashcode % data.length;
	}
	
	/**
	 * 방과 key값을 통해 방에서 key에 맞는 Node를 찾아서 반환
	 * @param list
	 * @param key
	 * @return
	 */
	Node searchKey(LinkedList<Node> list, String key) {
		if(list == null) {
			return null;
		}
		for(Node node : list) {
			if(node.key.equals(key)) {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * key와 value를 받아 hashcode값을 구하고
	 * hashcode값을 통해 방번호인 Index를 구한다.
	 * 이후 정해진 방인 LinkedList에 값을 Node로 저장한다.
	 * @param key
	 * @param value
	 */
	void put(String key, String value) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
//		System.out.println("key: " + key + ", hashcode: " + hashcode + ", index: " + index);
		LinkedList<Node> list = data[index];
		if(list == null) {
			list = new LinkedList<>();
			data[index] = list;
		}
		Node node = searchKey(list, key);
		if(node == null) {
			list.addLast(new Node(key, value));
		} else {
			node.value(value);
		}
	}
	
	/**
	 * 주어진 key값으로 hashcode를 먼저 계산한다.
	 * hashcode 값으로 방번호인 index를 찾고,
	 * 방인 LinkedList에서 key에 대한 value를 찾아 리턴한다.
	 * @param key
	 * @return
	 */
	String get(String key) {
		int hashcode = getHashCode(key);
		int index = convertToIndex(hashcode);
		LinkedList<Node> list = data[index];
		Node node = searchKey(list, key);
		return node == null ? "Not Found" : node.value;
	}
	
	public static void main(String[] args) {
		HashTable h = new HashTable(4);
		h.put("apple", "is computer company");
		h.put("banana", "is sweet");
		h.put("carrot", "is delicious");
		h.put("doctor", "is saving people");
		h.put("element", "is part of object");
		System.out.println(h.get("apple"));
		System.out.println(h.get("banana"));
		System.out.println(h.get("carrot"));
		System.out.println(h.get("doctor"));
		System.out.println(h.get("element"));
	}
}
