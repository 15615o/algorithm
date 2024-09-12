package baekjoon.Q2667;

/*
   1. 아이디어
   - 2중포문 돌면서 1 && 탐색하지 않은 주소 -> dfs 수행
   - dfs: 재귀함수로 주변의 1 탐색
   2. 시간복잡도
   - O(V + E) = O(N^2 + 4N^2) = O(N^2) = 625 < 2억 -> 가능
   3. 자료구조
   - int[] map
   - boolean[] chk
   - List paints
*/

import java.util.*;
import java.io.*;

public class Main{
    int[][] map;
    boolean[][] chk;
    int N = 0;
    public Integer dfs(int a, int b) {
        Integer size = 1;
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        for(int i=0; i<4; i++) {
            int ex = a + dx[i];
            int ey = b + dy[i];

            if(0 <= ex && ex < N && 0 <= ey && ey < N) {
                if(map[ex][ey] == 1 && !chk[ex][ey]) {
                    chk[ex][ey] = true;
                    size += dfs(ex, ey);
                }
            }
        }

        return size;
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        N = Integer.parseInt(infoSt.nextToken());

        map = new int[N][N];
        chk = new boolean[N][N];
        List<Integer> paints = new ArrayList<>();

        for(int i=0; i<N; i++) {
            String[] strs = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        for(int k=0; k<N; k++) {
            for(int l=0; l<N; l++) {
                if(map[k][l] == 1 && !chk[k][l]) {
                    chk[k][l] = true;
                    // dfs
                    paints.add(dfs(k, l));
                }
            }
        }

        Collections.sort(paints); // 정렬

        sb.append(paints.size()).append("\n");
        for(int m=0; m<paints.size(); m++){
            sb.append(paints.get(m));
            if(m < paints.size()-1) sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}