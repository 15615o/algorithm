package baekjoon.Q2178;

/*
    1. 알고리즘
    - 2차원 배열을 포문으로 반복하며 bfs 수행
    - 바깥으로 나가지 않고, chk 배열이 false이고, 1이면
    - 최소 거리 배열에 이전 경로 값 + 1
    2. 시간복잡도
    - O(N*M)
    3. 변수
    - 탐색 대상 그래프: int[][] map
    - 방문 확인 그래프: boolean[][] chk
    - 현재 위치: Point pt
    - 도착지까지의 최소 거리: int[][] dist;
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {1, 0, -1, 0};

    public int bfs(int[][] map, int N, int M) {
        boolean[][] chk = new boolean[N][M];
        int[][] dist = new int[N][M];
        dist[0][0] = 1;
        Queue<Point> q = new LinkedList<>();
        Point pt = new Point(0, 0);
        q.add(pt);

        while(!q.isEmpty()) {
            Point tPt = q.poll();
            int x = tPt.x;
            int y = tPt.y;

            if(x == M-1 && y == N-1){
                return dist[y][x];
            }

            for(int i=0; i<4; i++){
                int ex = DX[i] + x;
                int ey = DY[i] + y;

                if(ex >= 0 && ex < M && ey >= 0 && ey < N && map[ey][ex] == 1 && !chk[ey][ex]){ // 모든 탐색조건을 만족하면
                    dist[ey][ex] = dist[y][x] + 1;
                    chk[ey][ex] = true;
                    q.add(new Point(ex, ey));
                }
            }
        }

        return -1;
    }

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(infoSt.nextToken());
        int M = Integer.parseInt(infoSt.nextToken());
        int[][] map = new int[N][M];
        int result = 0;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String[] str = st.nextToken().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        result = bfs(map, N, M);

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
