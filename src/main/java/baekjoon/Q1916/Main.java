package baekjoon.Q1916;

/*
    1. 아이디어
    - 방향그래프, 최소 비용 탐색 -> 다익스트라
    - 인접리스트에 정보 저장
    - 초기값(0, 1) 힙에 넣고 힙이 빌 때까지 반복
    - 현재 힙의 값의 도착지를 시작 지점으로 하는 인접리스트를 반복하며 비용 + 현재 힙의 비용과 최소 비용 관리하는 배열의 값을 비교하여 갱신
    - 최소값 갱신이 되면 heap에 최소값 저장
    2. 시간복잡도
    - O((N + M)logN) =>
    3. 변수
    - ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
    - PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
    - int[] min = new int[N+1];
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        int[] min = new int[N+1];
        boolean[] chk = new boolean[N+1];

        // 인접리스트 초기화
        for(int i=0; i<N+1; i++){
            adjList.add(new ArrayList<>());
            min[i] = Integer.MAX_VALUE;
        }

        // 인접리스트 값 추가
        for(int j=0; j<M; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList.get(a).add(new int[]{c, b});
        }

        StringTokenizer infoSt = new StringTokenizer(br.readLine());
        int st = Integer.parseInt(infoSt.nextToken());
        int ed = Integer.parseInt(infoSt.nextToken());

        // 힙에 초기값 설정
        pq.add(new int[]{0, st});
        min[st] = 0;

        while(!pq.isEmpty()){
            int[] node = pq.poll();

            if(chk[node[1]]) continue;
            chk[node[1]] = true;

            for(int k=0; k<adjList.get(node[1]).size(); k++){
                int target = adjList.get(node[1]).get(k)[1];
                int targetCost = adjList.get(node[1]).get(k)[0];

                if(min[target] > node[0] + targetCost){
                    min[target] = node[0] + targetCost; // 최소 거리 발견하면 갱신
                    pq.add(new int[]{min[target], target});
                }
            }
        }

        bw.write(Integer.toString(min[ed]));
        bw.flush();
        bw.close();
    }
}
