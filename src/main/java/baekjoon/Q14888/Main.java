package baekjoon.Q14888;

/*
    1. 아이디어
    - 백트래킹
    - 재귀함수 안에서 포문(반복할 수 있는 개수만큼)
    - 가져온 수와 현재 수를 현재 연산자 기준 연산
    - 부호 우선순위 상관없이 바로 연산
    - 나눗셈은 몫만 취하고, 나누려는 값이 -일 경우, -1을 곱해 양수로 만든 뒤, 몫에 다시 -1을 곱함
    - 재귀 return 시 마다 최소, 최대값 갱신
    2. 시간복잡도
    - 연산자 저장: O(N-1)
    - 백트래킹: O(N!) = 4천만
    - 가능
    3. 변수
    - int[] A
    - int[] opers
    - int min
    - int max
 */

import java.util.*;
import java.io.*;

public class Main {
    public int[] backTracking(int num, int[] A, boolean[] chk, String[] opers, int st, int ed, int[] minMax) {
        if(st == ed){
            minMax[0] = Math.min(minMax[0], num); // 작은 수 갱신
            minMax[1] = Math.max(minMax[1], num); // 큰 수 갱신
            return minMax;
        }

        for(int i=0; i<opers.length; i++){

            if(!chk[i]){
                int tempNum = num;
                chk[i] = true;

                if(opers[i].equals("+")) tempNum += A[st];
                else if(opers[i].equals("-")) tempNum -= A[st];
                else if(opers[i].equals("*")) tempNum *= A[st];
                else if(opers[i].equals("/")){
                    if(tempNum < 0) tempNum = -(-tempNum / A[st]); // 음수인 경우
                    else tempNum /= A[st];
                }

                backTracking(tempNum, A, chk, opers, st+1, ed, minMax); // 백트래킹 수행
                chk[i] = false; // 체크 변수 초기화
            }
        }

        return minMax;
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        String[] opers = new String[N-1];
        boolean[] chk = new boolean[N-1];

        StringTokenizer ASt = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(ASt.nextToken());
        }

        StringTokenizer operSt = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int j=0; j<4; j++) {
            int loop = Integer.parseInt(operSt.nextToken()); // 반복할 수
            for(int k=0; k<loop; k++){
                if(j == 0){
                    opers[cnt] = "+";
                } else if(j == 1){
                    opers[cnt] = "-";
                } else if(j == 2){
                    opers[cnt] = "*";
                } else if(j == 3){
                    opers[cnt] = "/";
                }
                cnt++;
            }
        }

        int[] res = backTracking(A[0], A, chk, opers, 1, N, new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE});

        sb.append(res[1]).append("\n").append(res[0]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
