package baekjoon.Q1197;

/*
    1. 아이디어
    - MST 기본 문제, 코드 숙지
    - 간선 인접리스트에 넣기
    - 힙(priorityQueue)에 시작점 넣기
    - 힙이 빌 때까지 아래 작업 반복
        - 힙의 최소값 꺼낸 뒤 해당 노드 방문 안했으면 방문표시, weight 합산, 연결된 간선 힙에 넣기
    2. 시간복잡도
    - MST: O(ElogE)
    3. 자료구조
    - 간선 정보 저장(인접리스트): int[][](weight, node)
    - 힙: priorityQueue(weight, node)
    - 방문여부: boolean[] chk
    - MST 결과: int res
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(infoSt.nextToken());
        int E = Integer.parseInt(infoSt.nextToken());
        List<List<Integer[]>> edge = new ArrayList<>();
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(2, (a, b) -> Integer.compare(a[0], b[0]));
        boolean[] chk = new boolean[V+1];
        int res = 0;

        for(int i=0; i<V+1; i++) { // edge 초기화
            edge.add(new ArrayList<>());
        }

        for(int j=0; j<E; j++) { // 양방향으로 노드 정보 삽입
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edge.get(a).add(new Integer[] {c, b});
            edge.get(b).add(new Integer[] {c, a});
        }
        pq.add(new Integer[] {0, 1});

        while(!pq.isEmpty()){
            Integer[] node = pq.poll();
            if(!chk[node[1]]){
                chk[node[1]] = true;
                res += node[0];

                for(int k=0; k<edge.get(node[1]).size(); k++){
                    pq.add(edge.get(node[1]).get(k));
                }
            }
        }

        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
