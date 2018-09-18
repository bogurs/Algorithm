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
            int x = Integer.valueOf(line[2])-1;
            int y = Integer.valueOf(line[3])-1;
            
            boolean ok = false;
            for (int i = x; i < (m*n); i+=m) {
            	if (i%n == y) {
            		ok = true;
            		System.out.println(i+1);
            		break;
            	}
            }
            if (!ok) {
            	System.out.println(-1);
            }
		}
		
		bf.close();
	}

}
