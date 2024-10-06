package baekjoon.Q1922;

/*
    1. 아이디어
    - MST
    - 인접 리스트에 타겟 노드, 가중치 넣기
    - 힙에 시작점 (0, 1) 넣고 빌 때까지 반복
    - 현재 노드가 방문되지 않았으면 가중치 합산, 인접 노드 정보 힙에 삽입
    - 현재 노드가 방문되었다면 pass
    2. 시간복잡도
    - O(MlogM)
    3. 변수
    - ArrayList<ArrayList<int[]>> adjList
    - ProrityQueue<Point> pq
    - boolean[] chk
    - int cost
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
        boolean[] chk = new boolean[N+1];
        int cost = 0;

        // 인접리스트 초기화
        for(int i=0; i<N+1; i++){
            adjList.add(new ArrayList<>());
        }

        // 인접리스트에 값 삽입
        for(int j=0; j<M; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 노드
            int b = Integer.parseInt(st.nextToken()); // 타겟 노드
            int c = Integer.parseInt(st.nextToken()); // 가중치
            adjList.get(a).add(new int[]{c, b});
            adjList.get(b).add(new int[]{c, a});
        }

        pq.add(new int[]{0, 1});
        while(!pq.isEmpty()){
            int[] node = pq.remove();
            if(!chk[node[1]]){ // 탐색하려는 노드가 방문되지 않았으면
                chk[node[1]] = true; // 방문 체크
                pq.addAll(adjList.get(node[1])); // 인접리스트의 모든 배열 힙에 삽입
                cost += node[0]; // 최소 비용 cost에 합산
            }
        }

        bw.write(Integer.toString(cost));
        bw.flush();
        bw.close();
    }
}
