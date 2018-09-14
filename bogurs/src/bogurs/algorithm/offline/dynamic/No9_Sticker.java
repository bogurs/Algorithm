package bogurs.algorithm.offline.dynamic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 스티커
 * 
 * 스티커를 떼어냈을 때 떨어진 스티커의 최대 총 합
 * 
 * 스티커의 최대 크기는 2*100,000 이며,
 * 뜯거나 안뜯는 경우 2가지이므로 최대 2^200,000 가지이므로 브루트 포스로 풀지 못한다.
 * 
 * d[n] = 2n개의 스티커 중 일부를 떼어냈을 때 스티커 점수의 총합
 * d[n-1] = 2(n-1)개의 스티커 중 일부를 떼어냈을 때 스티커 점수의 총합
 * 스티커를 위나 아래 둘 중 어떤 것을 떼어야 할지 혹은 아예 떼지 않는게 총합에 좋은지를
 * 변수를 추가해서 점화식을 다시 세운다.
 * 
 * d[n][i] = 2n개의 스티커 중 0개(i=0) 또는 위(i=1), 아래(i=2)를 뜯었을 때의 최대값
 * d[n-1][0] = 2(n-1)개의 스티커 중 0개를 뜯었을 때 최대값
 * d[n-1][1] = 2(n-1)개의 스티커 중 위의 스티커를 뜯었을 때 최대값
 * d[n-1][2] = 2(n-1)개의 스티커 중 아래의 스티커를 뜯었을 때 최대값
 * 
 * d[n][0] = max(d[n-1][0], d[n-1][1], d[n-1][2])
 * d[n][1] = max(d[n-1][0], d[n-1][2]) + a[n][1]
 * d[n][2] = max(d[n-1][0], d[n-1][1]) + a[n][2]
 * 
 * d[1][0] = 0
 * d[1][1] = a[1][1]
 * d[1][2] = a[1][2]
 * 
 * @author thsong
 *
 */
public class No9_Sticker {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine()); // n (1 ≤ n ≤ 100,000)
			String[] nums1 = br.readLine().split(" "); // 점수 (0 <= 점수 <= 100)
			String[] nums2 = br.readLine().split(" ");
			
			
			int[][] nums = new int[n+1][3];
			for (int i = 1; i <= n; i++) {
				nums[i][1] = Integer.parseInt(nums1[i-1]);
				nums[i][2] = Integer.parseInt(nums2[i-1]);
			}
			int[][] d = new int[n+1][3];
			d[1][0] = 0;
			d[1][1] = nums[1][1];
			d[1][2] = nums[1][2];
			
			for (int i = 2; i <= n; i++) {
				d[i][0] = Math.max(Math.max(d[i-1][0], d[i-1][1]), d[i-1][2]);
				d[i][1] = Math.max(d[i-1][0], d[i-1][2]) + nums[i][1];
				d[i][2] = Math.max(d[i-1][0], d[i-1][1]) + nums[i][2];
			}
			
			int answer = Math.max(Math.max(d[n][0], d[n][1]), d[n][2]);
			System.out.println(answer);
		}
		
		br.close();
	}

}
