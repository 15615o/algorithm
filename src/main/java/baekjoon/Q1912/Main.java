package baekjoon.Q1912;

/*
1. 아이디어
- 배열을 반복하며 바로 이전 값에서 현재 값을 더한다
- 이전 값이 0보다 작으면 현재 값만 취한다
- 최종 배열에서 가장 큰 수를 출력한다
2. 변수
- int n
- int[] arr
- int[] dp
- int sum
- int result
3. 시간복잡도
- O(N)
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        for(int j=1; j<n; j++){
            dp[j] = Math.max(0, dp[j - 1]) + arr[j];
        }

        int result = -Integer.MAX_VALUE;
        for(int k=0; k<n; k++){
            result = Math.max(result, dp[k]);
        }

        System.out.print(result);
    }
}
