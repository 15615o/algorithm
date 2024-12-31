package baekjoon.Q1003;

// 16:30 - Start 16:52 - End
/*
1. 아이디어
- 0: 1,0 / 1: 0,1 / 2: 1,1 / 3: 1,2 / 4: 2,3 / 5: 3,5 / 6: 5,8
=>
- 2부터 가장 큰 수 까지 반복하며 배열에 값 저장
- 피보나치 공식에 의한 결과값을 배열에서 찾아서 바로 저장
- 원하는 수에 해당하는 배열의 주소를 찾아 bw에 저장
2. 변수
- int T
- int N
- int[] nums
- int max
- int[][] arr
3. 시간복잡도
- O(N)
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] nums = new int[T];
        int max = 0;

        for(int i=0; i<T; i++){
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, nums[i]);
        }

        int[][] arr = new int[max + 1][2];
        for(int j=0; j<=max; j++){
            if(j == 0){
                arr[j][0] = 1;
                arr[j][1] = 0;
            } else if(j == 1){
                arr[j][0] = 0;
                arr[j][1] = 1;
            } else {
                arr[j][0] = arr[j - 1][0] + arr[j - 2][0];
                arr[j][1] = arr[j - 1][1] + arr[j - 2][1];
            }
        }

        for(int num : nums){
            bw.write(arr[num][0] + " " + arr[num][1] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
