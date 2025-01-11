package baekjoon.Q1932;

/*
1. 아이디어
- dp(이중포문)
- 자기 자신과 왼쪽, 오른쪽 값을 각각 더한 값을 갱신
- 갱신할 때마다 기존 값과 비교하여 큰 값으로 갱신
- 마지막 배열 중 큰 값 출력
2. 변수
- int n
- int[][] arr
- int[][] dp
- int res
3. 시간복잡도
- O(N^2)
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        for(int i=0; i<n-1; i++){
            for(int j=0; j<=i; j++){
                for(int k=j; k<j+2; k++){
                    dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][j] + arr[i + 1][k]);
                }
            }
        }

        int res = 0;
        for(int i=0; i<n; i++){
            res = Math.max(res, dp[n - 1][i]);
        }

        System.out.print(res);
    }
}
