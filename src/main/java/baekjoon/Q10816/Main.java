package baekjoon.Q10816;

/*
    1. 아이디어
    - 상근의 배열로 upperBound, lowerBound 탐색
    - upperBound - lowerBound 리턴
    2. 시간복잡도
    - O(MlogN)
    3. 변수
    - int N
    - int M
    - int[] sg
 */

import java.util.*;
import java.io.*;

public class Main {
    private static int upperBound(int[] arr, int idx){ // 상한 탐색
        int hi = arr.length;
        int low = 0;
        int mid = 0;

        while(hi > low){
            mid = (hi + low) / 2;
            if(arr[mid] > idx){
                hi = mid;
            } else {
                low = mid+1;
            }
        }

        return hi;
    }

    private static int lowerBound(int[] arr, int idx){ // 하한 탐색
        int hi = arr.length;
        int low = 0;
        int mid = 0;

        while(hi > low){
            mid = (hi + low) / 2;
            if(arr[mid] >= idx){
                hi = mid;
            } else {
                low = mid+1;
            }
        }

        return low;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] sg = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sg[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sg);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++){
            int idx = Integer.parseInt(st.nextToken());
            sb.append(upperBound(sg, idx) - lowerBound(sg, idx)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
