package baekjoon.Q1463;

/*
1. 아이디어
- 2부터 반복하면서 목표 숫자까지 연산 수행
- -1 먼저 하고 arr 배열에 저장
- 가능한지 판단 후 2,3 나눈 주소값의 값 + 1을 arr 해당값과 비교하여 최소값 갱신
2. 변수
- int N
- int[] arr
3. 시간복잡도
- O(N)
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for(int i=2; i<=N; i++){
            arr[i] = arr[i - 1] + 1; // 이전 값에서 1번 계산한 값

            if(i % 3 == 0){
                arr[i] = Math.min(arr[i], arr[i / 3] + 1);
            }

            if(i % 2 == 0){
                arr[i] = Math.min(arr[i], arr[i / 2] + 1);
            }
        }

        System.out.print(arr[N]);
    }
}
