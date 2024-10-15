package baekjoon.Q16234;

/*
    1. 아이디어
    - BFS 돌면서 연합이 가능한 나라 2차원배열에 체크, 연합 인구 총합, 연합 국가 수++
    - 연합 인구 총합 / 연합 국가 수를 연합 나라의 인구로 대체, 일 수++
    - 연합이 가능하지 않을 때까지 반복
    2. 시간복잡도
    - O(N^2 * 2000) => 2500 * 2000 = 5,000,000 => 가능
    3. 변수
    - int[][] map
    - boolean[][] chk
    - int people
    - int countries
    - int days
 */

import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] chk;

    static boolean bfs(int N, int L, int R, int x, int y){ // 연합이 여러개일 때 고려
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> arr = new ArrayList<>();
        q.add(new int[] {x, y});
        arr.add(new int[] {x, y});
        int people = map[y][x];
        int countries = 1;
        chk[y][x] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            int nx = now[0];
            int ny = now[1];

            for (int k = 0; k < 4; k++) {
                int ex = dx[k] + nx;
                int ey = dy[k] + ny;

                if (ex >= 0 && ex < N && ey >= 0 && ey < N && !chk[ey][ex]
                    && (L <= Math.abs(map[ey][ex] - map[ny][nx]) && Math.abs(map[ey][ex] - map[ny][nx]) <= R)) {

                    q.add(new int[] {ex, ey});
                    arr.add(new int[] {ex, ey});
                    people += map[ey][ex];
                    countries++;
                    chk[ey][ex] = true;
                }
            }
        }

        if(countries > 1){
            double avg = Math.floor(1.0 * people / countries);
            for(int[] coord : arr){
                map[coord[1]][coord[0]] = (int) avg;
            }
            return true;
        }

        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int days = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isMovable = true;
        while(isMovable) {
            isMovable = false;
            boolean moved = false;
            chk = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++) {
                    if(!chk[i][j]){
                        if(bfs(N, L, R, j, i)){
                            moved = true;
                        }

                    }
                }
            }
            if(moved){
                isMovable = true;
                days++;
            }
        }

        bw.write(Integer.toString(days));
        bw.flush();
        bw.close();
    }

}
