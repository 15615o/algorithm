package baekjoon.Q3190;

/*
    1. 아이디어
    - 맵에 사과 1로 표시
    - while문 돌면서 뱀의 길이의 0번째에 현재 방향+1만큼 추가
    - 벽, 자신과 닿으면 종료
    - 이동한 칸에 사과가 있으면 사과 제거
    - 사과가 없다면 뱀의 꼬리 제거
    - 게임이 몇 초에 끝나는지 리턴
    2. 시간복잡도
    - 소요시간: O(X) = 10,000
    - 충돌여부 확인: O(N) = 100
    - 10,000 * 100 = 1,000,000 => 가능
    3. 변수
    - 맵 크기: int N
    - 사과 개수: int K
    - 방향 전환 횟수: int L
    - 맵 배열: int[][] map
    - 방향 전환 시간: int[] times
    - 방향 전환 정보: String[] drtns
    - 현재 방향 정보 주소값: int drtnAddr
    - 뱀: Deque snake = new LinkedList<>()
    - 소요시간: int sec
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int i=0; i<K; i++){
            StringTokenizer appleSt = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(appleSt.nextToken());
            int x = Integer.parseInt(appleSt.nextToken());
            map[y-1][x-1] = 1; // 사과 표시
        }

        int L = Integer.parseInt(br.readLine());
        int[] times = new int[L]; // 방향 전환 시간
        String[] drtns = new String[L]; // 전환할 방향

        for(int k=0; k<L; k++){
            StringTokenizer drtnSt = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(drtnSt.nextToken());
            String drtn = drtnSt.nextToken();
            times[k] = time;
            drtns[k] = drtn;
        }

        int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌
        int[] dx = {0, 1, 0, -1};
        int dAddr = 1; // dx, dy
        int drtnAddr = 0; // times, drtns

        Deque<Point> snake = new LinkedList<>();
        int sec = 0; // 소요시간

        snake.add(new Point(0, 0)); // 초기 뱀 위치 설정
        map[0][0] = 2;
        while(true){

            if(sec == times[drtnAddr]){
                if(drtns[drtnAddr].equals("L")){
                    dAddr = (dAddr + 3) % 4; // 왼쪽으로 방향 전환
                } else if(drtns[drtnAddr].equals("D")) {
                    dAddr = (dAddr + 1) % 4; // 오른쪽으로 방향 전환
                }
                if(times.length-1 > drtnAddr) {
                    drtnAddr++;
                }
            }

            Point head = snake.peekLast(); // 헤드값 추출

            int snakeY = head.y + dy[dAddr]; // 늘어날 y값
            int snakeX = head.x + dx[dAddr]; // 늘어날 x값

            // 맵 밖을 벗어나지 않고, 뱀의 몸이 아닌 경우
            if(0 <= snakeY && snakeY < N && 0 <= snakeX && snakeX < N && map[snakeY][snakeX] != 2){
                snake.add(new Point(snakeX, snakeY)); // 현재 방향으로 길이 증가

                if(map[snakeY][snakeX] != 1) { // 사과가 있지 않으면
                    Point rm = snake.remove(); // 뱀에서 꼬리 제거
                    map[rm.y][rm.x] = 0; // map에서 꼬리 제거
                }

                map[snakeY][snakeX] = 2; // map에 뱀 체크
            } else {
                break;
            }

            sec++; // 초 증가
        }

        bw.write(Integer.toString(sec+1));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
