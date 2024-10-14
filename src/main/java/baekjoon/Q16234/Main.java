package baekjoon.Q16234;

/*
    1. 아이디어
    - BFS 돌면서 연합이 가능한 나라 2차원배열에 체크, 연합 인구 총합, 연합 국가 수++
    - 연합 인구 총합 / 연합 국가 수를 연합 나라의 인구로 대체, 일 수++
    - 연합이 가능하지 않을 때까지 반복
    2. 시간복잡도
    - O(N^2 * N^2) => 50^4 = 6,250,000 => 가능
    3. 변수
    - int[][] map
    - boolean[][] chk(지역변수)
    - int people(지역변수)
    - int countries(지역변수)
    - int days
    - Queue q
 */

import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static boolean isMovable = true;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] chk;
    static int days = 0;

    static void bfs(int N, int L, int R){ // 연합이 여러개일 때 고려
        int people = 0;
        int countries = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) {
                for (int k = 0; k < 4; k++) {
                    int ex = dx[k] + j;
                    int ey = dy[k] + i;

//                    System.out.println(Math.abs(map[ey][ex] - map[i][j]));
                    if (ex >= 0 && ex < N && ey >= 0 && ey < N && (!chk[i][j] || !chk[ey][ex])
                            && (L <= Math.abs(map[ey][ex] - map[i][j]) && Math.abs(map[ey][ex] - map[i][j]) <= R)) {
                        System.out.println("map["+ey+"]["+ex+"] - map["+i+"]["+j+"] = " + Math.abs(map[ey][ex] - map[i][j]));
                        System.out.println("map["+ey+"]["+ex+"] - map["+i+"]["+j+"] = " + (map[ey][ex] - map[i][j]));
                        if(!chk[i][j]){ // 현재 국가가 체크되지 않았을 경우(중복합산 방지)
                            people += map[i][j];
                            countries++;
                            chk[i][j] = true;
                        }
                        if(!chk[ey][ex]){ // 주변 국가가 체크되지 않았을 경우(중복합산 방지)
                            people += map[ey][ex];
                            countries++;
                            chk[ey][ex] = true;
                        }
                    }
                }
            }
        }
        System.out.println();

        if(countries > 0){
            double avg = Math.floor(1.0 * people / countries);
            System.out.println(people + ", " + countries);
            for(int l=0; l<N; l++){
                for(int m=0; m<N; m++){
                    if(chk[l][m]) map[l][m] = (int) avg;
                }
            }
            days++;
        } else {
            isMovable = false;
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        System.out.println(isMovable);
        while(isMovable) {
            chk = new boolean[N][N];
            bfs(N, L, R);
        }

        bw.write(Integer.toString(days));
        bw.flush();
        bw.close();
    }

}
