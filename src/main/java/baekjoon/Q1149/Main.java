package baekjoon.Q1149;

/*
1. 아이디어
- 반복문 돌면서 이전 최소값(dp) + 가능한 값을 더한 값을 현재 dp 배열에 각각 저장 => 점화식
- 마지막 dp 배열에서 최소값을 출력
2. 변수
- int N
- int[][] arr
- int[][] dp
3. 시간복잡도
- O(N)
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[][] dp = new int[N][3];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for(int j=1; j<N; j++){
            dp[j][0] = arr[j][0] + Math.min(dp[j - 1][1], dp[j - 1][2]);
            dp[j][1] = arr[j][1] + Math.min(dp[j - 1][0], dp[j - 1][2]);
            dp[j][2] = arr[j][2] + Math.min(dp[j - 1][0], dp[j - 1][1]);
        }

        System.out.print(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}
