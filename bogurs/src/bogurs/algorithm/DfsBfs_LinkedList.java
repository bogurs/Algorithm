package bogurs.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DfsBfs_LinkedList {

	static LinkedList<Integer>[] list;
	static boolean[] visited;
	
	public static void dfsR(Stack<Integer> s, int numOfNode) {
		int i = s.pop();
		System.out.print(i + " ");
		
		visited[i] = true;
		
		while (!list[i].isEmpty()) {
			int liVal = list[i].removeFirst();
			if (!visited[liVal]) {
				s.push(liVal);
				dfsR(s, numOfNode);
			}
		}
	}
	
	public static void dfs(int startPoint, int numOfNode) {
		Stack<Integer> s = new Stack<>();
		s.push(startPoint);
		
		while(!s.isEmpty()) {
			int temp = s.pop();
			visited[temp] = true;
			
			while (!list[temp].isEmpty()) {
				int liVal = list[temp].removeFirst();
				if (!visited[liVal]) {
					s.push(liVal);
					visited[liVal] = true;
				}
			}
			
			System.out.print(temp + " ");
		}
	}

	public static void bfs(int startPoint, int numOfNode) {
		Queue<Integer> q = new LinkedList<>();
		q.add(startPoint);
		
		while(!q.isEmpty()) {
			int temp = q.remove();
			visited[temp] = true;
			
			while (!list[temp].isEmpty()) {
				int liVal = list[temp].removeFirst();
				if (!visited[liVal]) {
					q.add(liVal);
					visited[liVal] = true;
				}
			}
			
			System.out.print(temp + " ");
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 정점의 개수 N(1 ≤ N ≤ 1,000)
		int m = sc.nextInt(); // 간선의 개수 M(1 ≤ M ≤ 10,000)
		int v = sc.nextInt(); // 탐색을 시작할 정점의 번호 V
		
		list = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new LinkedList<>();
		}
		visited = new boolean[n + 1];

		for (int i = 1; i <= m; i++) { // Graph 만들기
			int begin = sc.nextInt();
			int end = sc.nextInt();
			
			list[begin].add(end);
			list[end].add(begin);
		}

		Stack<Integer> s = new Stack<>(); // DFS를 위한 스택 초기화
		s.push(v); // 시작 정점을 스택에 push
		dfsR(s, n);
//		dfs(v, n);
//		bfs(v, n);

		sc.close();
	}
}
