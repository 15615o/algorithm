package baekjoon.Q1699;

/*
1. 아이디어
- 목표 수 까지 반복하고 이중포문으로 제곱수만 반복
- 현재 수에서 제곱근을 곱한 값 + 1과 현재 값을 비교하여 작은 값 갱신 => 점화식
- dp 배열의 목표 수 값 출력
2. 변수
- int n
- int[] dp
3. 시간복잡도
- O(N√N)
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for(int i=1; i<=n; i++){
            dp[i] = i; // 최악의 경우로 초기화
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j*j<=i; j++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // 최적의 경우의 수 갱신
            }
        }

        System.out.print(dp[n]);
    }
}
