package baekjoon.Q1920;
/*
    1. 아이디어
    - N 배열 정렬
    - M 배열 for문 돌면서 M이 있는지 이진탐색
    2. 시간복잡도
    - N 정렬 -> O(NlogN)
    - N에서 M 이진탐색 -> O(M * logN)
    3. 자료구조
    - int[] N
 */
import java.util.*;
import java.io.*;

public class Main {
    public String biSearch(int[] nArr, int nSAdrr, int nEAdrr, int mVal) {
        String rs;
        if(nSAdrr == nEAdrr){ // 종료 조건(일치할 경우)
            if(nArr[nSAdrr] == mVal) return "1";
            else return "0";
        }

        int calcAddr = (nSAdrr + nEAdrr) / 2;
        if(nArr[calcAddr] < mVal){
            rs = biSearch(nArr, calcAddr + 1, nEAdrr, mVal);
        } else {
            rs = biSearch(nArr, nSAdrr, calcAddr, mVal);
        }
        return rs;
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer nSt = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(nSt.nextToken());
        int[] nArr = new int[N];

        StringTokenizer nArrSt = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nArr[i] = Integer.parseInt(nArrSt.nextToken());
        }
        Arrays.sort(nArr);

        StringTokenizer mSt = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(mSt.nextToken());
        int[] mArr = new int[M];

        StringTokenizer mArrSt = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++) {
            mArr[j] = Integer.parseInt(mArrSt.nextToken());
        }

        for(int k=0; k<M; k++){
            sb.append(biSearch(nArr, 0, N-1, mArr[k]));
            if(k < M-1) sb.append("\n"); // StringBuilder로 처리
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
