package baekjoon.Q2468;

/*
    1. 아이디어
    - 입력값 저장하면서 TreeSet에 비 높이 저장(rain)
    - TreeSet foreach 돌면서 dfs 수행, 최대값 갱신
    - dfs
        - map 밖으로 나가지 않고, 방문하지 않았고, rain값 초과이면
        - cnt+1, chk = true
        - 재귀 수행
        - map 순회 완료 시 cnt값 리턴
    2. 시간복잡도
    - dfs: O(N^2)
    - O(N^2*N) = O(N^3) = 100 * 100 * 100 < 2억 -> 가능
    3. 변수
    - int[][] map
    - boolean[][] chk
    - int maxRain
    - int maxSafeArea
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    int[] DX = {0, 1, 0, -1};
    int[] DY = {-1, 0, 1, 0};

    void dfs(int[][] map, boolean[][] chk, int rain, Point pt, int N) {
        chk[pt.y][pt.x] = true;

        for(int i=0; i<4; i++){
            int ex = pt.x + DX[i];
            int ey = pt.y + DY[i];

            if(ex >= 0 && ex < N && ey >= 0 && ey < N && !chk[ey][ex] && map[ey][ex] > rain) {
                dfs(map, chk, rain, new Point(ex, ey), N);
            }
        }
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int maxRain = 0;
        int maxSafeArea = 0;

        for(int i=0; i<N; i++){
            String[] arr = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                int h = Integer.parseInt(arr[j]); // 건물의 높이
                maxRain = Math.max(h, maxRain);
                map[i][j] = h;
            }
        }

        for(int rain=0; rain<maxRain; rain++) {
            boolean[][] chk = new boolean[N][N]; // 새로운 비 높이로 계산할 때마다 방문여부 초기화
            int cnt = 0;
            for(int k=0; k<N; k++) {
                for (int l = 0; l<N; l++) {
                    if(map[k][l] > rain && !chk[k][l]){
                        dfs(map, chk, rain, new Point(l, k), N);
                        cnt++;
                    }
                }
            }
            maxSafeArea = Math.max(maxSafeArea, cnt); // 최대값 갱신
        }

        bw.write(Integer.toString(maxSafeArea));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
