package baekjoon.Q1926;
/*
    1. 아이디어
        2중 for -> 1 && 탐색 x -> BFS
        BFS 돌면 그림 개수 +1, 최대 넓이 변경
    2. 시간복잡도
        BFS -> O(v+e)
        v -> m * n = 500 * 500 = 250,000
        e -> 4 * v
        v + e = 5v = 1,250,000 < 2억
    3. 자료구조
        그래프 전체 지도: int[][]
        탐색 여부: boolean[][]
        BFS: Queue
 */

import java.io.*;
import java.util.*;

class Point{
    Integer x = 0;
    Integer y = 0;
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(infoSt.nextToken());
        int n = Integer.parseInt(infoSt.nextToken());
        int[][] map = new int[m][n];
        boolean[][] chk = new boolean[m][n];
        Queue<Point> q = new LinkedList<Point>();

        int cnt = 0; // 그림의 개수
        int max = 0; // 그림의 최대 넓이

        // 입력값 int[][]에 저장(그래프 지도)
        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++) {
                if(map[i][j] == 1 && !chk[i][j]){ // 값이 1이고, 탐색하지 않은 영역
                    int tMax = 1;
                    chk[i][j] = true;
                    cnt++; // 그림 개수 증가
                    // bfs
                    int[] dx = {0, 1, 0, -1}; // 상, 우, 하, 좌
                    int[] dy = {-1, 0, 1, 0};

                    q.add(new Point(i, j));
                    while(!q.isEmpty()){
                        Point p = q.poll();
                        for(int k=0; k<4; k++){
                            int ex = p.x + dx[k];
                            int ey = p.y + dy[k];

                            if(0 <= ex && ex < m && 0 <= ey && ey < n){
                                if(map[ex][ey] == 1 && !chk[ex][ey]) { // map밖으로 나가지 않는 선에서 값이 1인게 있는지
                                    chk[ex][ey] = true;
                                    q.add(new Point(ex, ey));
                                    tMax++;
                                }
                            }
                        }
                    }
                    if(max < tMax){
                        max = tMax;
                    }
                }
            }
        }

        bw.write(cnt + "\n" + max); // 결과값 출력
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
