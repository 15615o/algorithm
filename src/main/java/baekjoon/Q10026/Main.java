package baekjoon.Q10026;

/*
    1. 아이디어
    - DFS
    - 이중 for문 반복하면서 dfs 재귀 탐색
    - 재귀 결과는 누적 합산
    - 상하좌우 방문하면서 map 밖으로 나가지 않고, 방문하지 않았고(chk) map의 현재 주소 값과 값이 동일하면 dfs 탐색
    - 상하좌우 방문하면서 map 밖으로 나가지 않고, 방문하지 않았고(colorChk) map의 현재 주소 값이 R 혹은 G이면 dfs 탐색, B일 때도 dfs 탐색
    - 더이상 탐색할 수 없으면 1 리턴
    2. 시간복잡도
    - O(2*N*N) => O(N*N) => 가능
    3. 변수
    - String[][] map
    - boolean[][] chk
    - boolean[][] colorChk
    - int res
    - int colorRes
 */

import java.io.*;

public class Main {
    private static int N = 0;
    private static String[][] map;
    private static boolean[][] colorChk;
    private static boolean[][] blindChk;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static void dfs(int[] now, boolean[][] chk, boolean isColorBlind){
        int y = now[0];
        int x = now[1];

        if(isColorBlind) blindChk[y][x] = true;
        else colorChk[y][x] = true;

        for(int i=0; i<4; i++){
            int ex = x + dx[i];
            int ey = y + dy[i];

            if(ex >= 0 && ex < N && ey >= 0 && ey < N && !chk[ey][ex]){
                if(isColorBlind){
                    if((map[y][x].equals("R") || map[y][x].equals("G")) && (map[ey][ex].equals("R") || map[ey][ex].equals("G"))){
                        dfs(new int[]{ey, ex}, chk, isColorBlind);
                    } else if(map[y][x].equals("B") && map[ey][ex].equals("B")){
                        dfs(new int[]{ey, ex}, chk, isColorBlind);
                    }
                } else {
                    if(map[y][x].equals(map[ey][ex])) {
                        dfs(new int[]{ey, ex}, chk, isColorBlind);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        colorChk = new boolean[N][N];
        blindChk = new boolean[N][N];
        int colorRes = 0;
        int blindRes = 0;

        // map에 그림 정보 저장
        for(int i=0; i<N; i++){
            map[i] = br.readLine().split("");
        }

        for(int j=0; j<N; j++){
            for(int k=0; k<N; k++){
                if(!colorChk[j][k]){
                    dfs(new int[]{j, k}, colorChk, false);
                    colorRes++;
                }
                if(!blindChk[j][k]){
                    dfs(new int[]{j, k}, blindChk, true);
                    blindRes++;
                }
            }
        }

        sb.append(colorRes).append(" ").append(blindRes);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
