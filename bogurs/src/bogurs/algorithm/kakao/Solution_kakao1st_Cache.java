package bogurs.algorithm.kakao;

import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;

public class Solution_kakao1st_Cache {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		final int CACHE_MISS = 5;
		final int CACHE_HIT = 1;
		
		if (cacheSize == 0 || cacheSize > cities.length) {
			return CACHE_MISS * cities.length;
		}
		
		Deque<String> cache = new LinkedList<>();
		for (int i = 0; i < cities.length; i++) {
			cities[i] = cities[i].toLowerCase();
			if (i < cacheSize) {
				answer += CACHE_MISS;
			} else {
				if (cache.contains(cities[i])) {
					cache.remove(cities[i]);
					answer += CACHE_HIT;
				} else {
					cache.remove();
					answer += CACHE_MISS;
				}
			}
			cache.addLast(cities[i]);
		}
		
		return answer;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Solution_kakao1st_Cache solution = new Solution_kakao1st_Cache();
//		int cacheSize = 3; //0 ≦ cacheSize ≦ 30 
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", 
//				"LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}; //최대 도시 수는 100,000
//		System.out.println(solution.solution(cacheSize, cities));
		
		int cacheSize2 = 30; //0 ≦ cacheSize ≦ 30
		String[] cities2 = {"Jeju", "Pangyo", "Seoul", "NewYork", 
				"LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA",
				"Jeju", "Pangyo", "Seoul", "NewYork"}; //최대 도시 수는 100,000
		System.out.println(solution.solution(cacheSize2, cities2));
	}
}
