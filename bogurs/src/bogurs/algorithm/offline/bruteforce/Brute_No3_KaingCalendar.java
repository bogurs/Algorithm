package bogurs.algorithm.offline.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 카잉 달력
 * 
 * 첫번째 해 <1:1>, 두번째 해 <2:2>...라고 하고
 * M, N, x, y값이 주어지면 <M:N> 달력법을 따르는 규칙에서
 * <x:y>는 몇 번째 해인지 구하는 문제
 * 
 * t 당 최대 M과 N의 최소공배수 만큼 루프를 돈다.
 * 모두 다 해보는 브루트 포스 문제로 생각하고 풀면 시간 초과가난다.
 * 이 경우 모두 다 하는 경우에서 건너 뛸 수 있는 경우를 생각해야 한다.
 * @author thsong
 *
 */
public class Brute_No3_KaingCalendar {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(bf.readLine());
        
        while (t-- > 0) {
        	String[] line = bf.readLine().split(" ");
            int m = Integer.valueOf(line[0]);
            int n = Integer.valueOf(line[1]);
            int x = Integer.valueOf(line[2]);
            int y = Integer.valueOf(line[3]);
			int nx = x;
			int ny = y;
			
			int l = 0;
			int g = 0;
			int small = 0;
			int cnt = 0;
			if (m < n) {
				small = m;
				y = cnt = x;
			} else {
				small = n;
				x = cnt = y;
			}
			for (int j = 1; j < small/2+1; j++) {
				if (m % j != 0) continue;
				if (n % j != 0) continue;
				g = j;
			}
			
			l = (m * n) / g;
			
			while (cnt < l) {
				if (x == nx && y == ny) {
					break;
				}
				
				x = (x + small) % m;
				y = (y + small) % n;
				cnt += small;
			}
			if (cnt > l) {
				System.out.println(-1);
			} else {
				System.out.println(cnt);
			}
		}
		
		bf.close();
	}

}
