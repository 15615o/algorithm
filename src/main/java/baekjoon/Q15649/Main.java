package baekjoon.Q15649;
/*
    1. 아이디어
    - 재귀함수(String[] target, boolean[] chk, int cnt) 안에 반복문
    - 두 번째 정수만큼 반복
    - cnt 가 0이 되면 종료
    2. 시간복잡도
    - O(N!) -> 가능(N = 10 까지는 백트래킹 사용 가능)
    3. 자료구조
    - int cnt
    - String[] target
    - boolean[] chk
 */

import java.util.*;
import java.io.*;

public class Main {
    public void backTracking(String[] target, boolean[] chk, List<String> strs, int st, int ed, BufferedWriter bw) throws IOException{
        if(st == ed) {
            String rst = String.join(" ", strs);
            bw.write(rst + "\n");
        }

        for(int i=0; i<target.length; i++){
            if(!chk[i]) {
                strs.add(target[i]);
                chk[i] = true;
                backTracking(target, chk, strs, st + 1, ed, bw);
                chk[i] = false;
                strs.remove(strs.size() - 1);
            }
        }
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] target = new String[N];

        for(int i=0; i<N; i++){
            target[i] = "" + (i + 1);
        }

        for(int j=0; j<target.length; j++){
            boolean[] chk = new boolean[N];
            chk[j] = true;
            List<String> strs = new ArrayList<String>();
            strs.add(target[j]);
            backTracking(target, chk, strs, 1, M, bw);
        }
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
