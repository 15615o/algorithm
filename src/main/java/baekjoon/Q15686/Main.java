package baekjoon.Q15686;

/*
    1. 아이디어
    - 모든 치킨집 정보를 리스트에 저장
    - 모든 건물 정보를 리스트에 저장
    - 탐색해야할 치킨집 정보를 리스트에 저장(백트래킹) -> 이 때, 리스트의 삽입, 삭제 최소화
    - 탐색해야할 치킨집 리스트의 길이가 M과 같을 때 최소 거리 탐색 수행
    - 건물마다 치킨집 정보와 비교하여 가장 작은 값을 minDistance(초기값 max)에 저장.
    - 한 건물의 minDistance 연산이 끝나면 totDistance에 합산
    2. 시간복잡도
    - O((13CM)*2N*M) => O((13C(6, 7일 때 최대)))*2N*M) => 2000 * 100 * 13 = 2,600,000 => 가능
    3. 변수
    - int[][] map
    - ArrayList<int[]> chickenRestaurants
    - ArrayList<int[]> searchChickenRestaurants
    - ArrayList<int[]> buildings
    - int res
 */

import java.util.*;
import java.io.*;

public class Main {
    private static int M;
    private static int N;
    private static int[][] map;

    private static int calculateDistance(ArrayList<int[]> buildings, ArrayList<int[]> searchChickenRestaurants){
        int totDistance = 0;

        for(int[] building : buildings){
            int minDistance = Integer.MAX_VALUE;
            for(int[] searchChickenRestaurant : searchChickenRestaurants){
                minDistance = Math.min(minDistance, Math.abs(building[0] - searchChickenRestaurant[0]) + Math.abs(building[1] - searchChickenRestaurant[1]));
            }
            totDistance += minDistance;
        }

        return totDistance;
    }

    private static int bt(ArrayList<int[]> chickenRestaurants, ArrayList<int[]> searchChickenRestaurants, ArrayList<int[]> buildings, int st, int res){
        if(searchChickenRestaurants.size() == M) { // 치킨거리 탐색
            int distance = calculateDistance(buildings, searchChickenRestaurants);
            return Math.min(distance, res);
        }
        // 백트래킹
        for (int j = st; j < chickenRestaurants.size(); j++) {
            searchChickenRestaurants.add(chickenRestaurants.get(j)); // 치킨집 정보를 삭제하지 않고 추가
            res = bt(chickenRestaurants, searchChickenRestaurants, buildings, j + 1, res);
            searchChickenRestaurants.remove(searchChickenRestaurants.size() - 1); // 저장한 탐색대상 치킨집 정보 제거
        }

        return res;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ArrayList<int[]> buildings = new ArrayList<>();
        ArrayList<int[]> chickenRestaurants = new ArrayList<>();
        ArrayList<int[]> searchChickenRestaurants = new ArrayList<>();
        int res = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int building = Integer.parseInt(st.nextToken());
                map[i][j] = building;
                if(building == 1) buildings.add(new int[]{i, j}); // 건물의 행, 열 정보 저장
                else if(building == 2) chickenRestaurants.add(new int[]{i, j}); // 치킨집의 행, 열 정보 저장
            }
        }

        res = bt(chickenRestaurants, searchChickenRestaurants, buildings, 0, res);

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
}
