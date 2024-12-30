package baekjoon.Q11726;
/*
    1. 아이디어
    - 2x1 -> 1가지, 2x2 -> 2가지, 2x3 -> 3가지, 2x4 -> 5가지, 2x5 -> 8가지
    -  점화식 -> N = N-1 + N-2
    - for문 돌면서 배열에 점화식 값 저장
    - 제출: arr[n] % 10007
    2. 시간 복잡도
    - O(N)
    3. 자료구조
    - int[] arr
 */
import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        arr[0] = 1;

        if(n > 1){
            arr[1] = 2;
        }
        if(n >= 3) { // n값이 3이상인 경우
            for(int i=2; i<n; i++){
                arr[i] = (arr[i-1] + arr[i-2]) % 10007;
            }
        }
        bw.write("" + arr[n-1]);
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}