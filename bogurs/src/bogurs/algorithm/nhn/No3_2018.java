package bogurs.algorithm.nhn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Sale{
	int exNum;
	int index;
	public Sale(int exNum, int index) {
		this.exNum = exNum;
		this.index = index;
	}
}
/**
 * 전부다해보기
 * 
 * 3. 수익 최대화
  문제의 입력값은 각 언어의 표준입력(stdin) 함수를, 출력값은 표준출력(stdout) 함수를 사용해주세요.
개발자인 ‘엔터’는 재테크 수단으로 가상화폐인 ‘NHN 코인(이하 코인)’에 투자하기로 했습니다.
‘코인’을 분석한 끝에 미래의 가격을 하루 단위로 예측하는 데 성공했습니다.
주어진 가격 예측 데이터를 기반으로 ‘코인’을 사고 팔아서 얻을 수 있는 최대 이익을 구하는 것이 이 문제의 목표입니다.



[조건]

하루에 ‘코인’을 사고파는 것을 모두 할 수는 없습니다. ‘코인’을 사거나 팔거나 둘 중 하나만 할 수 있습니다.
투자가 불필요하다고 판단되는 날에는 사거나 팔지 않고 건너뛸 수 있습니다.
매일 최대 한 개의 코인을 살 수 있고, 자본금은 무한하다고 가정합니다.
팔 때는 여러 개의 코인을 한꺼번에 팔 수 있습니다.
팔 때마다 ‘코인’ 개수와 상관없이 수수료 1원을 내야 합니다(하단 예시 참조).


[입력]

첫 번째 줄에는 가격을 예측한 날짜 수(days)가 정수로 입력됩니다.
1 < days <= 1000
두 번째 줄에는 각 날짜의 ‘코인’ 가격(price)이 공백문자로 구분되어 날짜순으로 입력됩니다.
1 <= price <= 10000
각 줄은 개행문자(newline, \n)로 구분됩니다.


[출력]

최대 이익을 정수로 출력합니다.


[예시 1]

입력
5
1 1 10 5 7
출력
18
설명
첫째 날과 둘째 날 ‘코인’을 한 개씩 산 후, 셋째 날 팔아서 얻은 이익은 ‘18원 - 수수료 1원’입니다.
넷째 날 ‘코인’을 산 후, 다섯째 날 모두 팔아서 얻은 이익은 ‘2원 - 수수료 1원’입니다.
따라서 최대 이익은 ‘18원’입니다.


[예시 2]

입력
5
5 4 3 2 1
출력
0
설명
시간이 흐를수록 가격이 내려가므로 투자를 하지 않는 것이 최선입니다.
따라서 최대 이익은 0원입니다.


[예시 3]

입력
5
5 10 5 10 5
출력
9
설명
수수료를 줄이기 위해 둘째 날에는 팔지 않는 것이 최선입니다.
첫째 날과 셋째 날 ‘코인’을 산 후, 넷째 날 한꺼번에 파는 것이 최대 이익을 얻을 수 있습니다.
따라서 최대 이익은 ‘10원 - 수수료 1원’입니다.

입/출력 예시
⋇ 입출력 형식을 잘 지켜주세요.
␣ : 공백
↵ : 줄바꿈
−⇥ : 탭
보기 입력 1
5
1 1 10 5 7
출력 1
18

보기 입력 2
5
5 4 3 2 1
출력 2
0

보기 입력 3
5
5 10 5 10 5
출력 3
9

 * @author thsong
 *
 */
class No3_2018 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		//5
		//1 1 10 5 7
		int n = sc.nextInt(); // 1 < days <= 1000
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = sc.nextInt(); // 1 <= price <= 10000
		}
		
		Queue<Sale> q = new LinkedList<>(); //판매 결과 및 판매 시점을 저장하는 배열
		for (int i = 0; i < n; i++) {
			int nowEx = 0;
			int res = 0;
			int idx = 0;
			for (int j = i+1; j < n; j++) {
				//현재 요소 p[i]값과 앞으로의 기댓값 (p[i+1]~p[n-1])과의 차이를 구해서 살 것인지 결정한다.
				//만약, 차이가 (+)요소가 있다면 주식을 산다.
				//가장큰 (+)가 되는 인덱스 값을 저장한다. (그 때 팔아야 되므로)
				//판매한 횟수를 모두 구한다.(횟수는 곧 수수료이므로, 수수료전체=판매횟수*-1)
				nowEx = p[j] - p[i]; // 기댓값을 계속 저장.
				if (nowEx >= res) {
					res = nowEx; //만약, 구한 기댓값이 가장 큰 경우 수익으로 교체.
					idx = j;
				}
			}
			if (res > 0) {
				q.add(new Sale(res, idx));
			}
		}
		
		int sum = 0;
		int idxCnt = 0;
		int nowIdx = 0;
		for (Sale s : q) {
			sum += s.exNum; // 기댓값을 모두 누적
			if (nowIdx == 0) {
				nowIdx = s.index;
				idxCnt++;
			}
			if (nowIdx != s.index) {
				idxCnt++;
			}
		}
		
		System.out.println(sum - idxCnt);
		sc.close();
	}
}