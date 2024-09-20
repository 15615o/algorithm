package baekjoon.Q1753;

/*
    1. 아이디어
    - 인접리스트에 입력값 삽입
    - node 배열 초기값 무한대로 설정
    - 시작점 거리는 0으로 세팅
    - 힙에서 하나씩 빼고 인접리스트에서 가장 작은 비용 더하기
    2. 시간복잡도
    - 다익스트라: O(ElogV)
    3. 변수
    - 힙: PriorityQueue<Integer[]> pq
    - 거리값 배열: int[] dist
    - 인접리스트: ArrayList<ArrayList<Integer[]>> node
 */

import java.util.*;
import java.io.*;

public class Main {
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer infoSt = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(infoSt.nextToken());
        int E = Integer.parseInt(infoSt.nextToken());

        StringTokenizer bgnSt = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(bgnSt.nextToken());
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(2, (a, b) -> Integer.compare(a[0], b[0]));
        int[] dist = new int[V+1];
        ArrayList<ArrayList<Integer[]>> node = new ArrayList<>();

        // 거리 배열 초기화
        for(int i=1; i<V+1; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K] = 0;

        // node 초기화
        for(int h=0; h<V+1; h++) { // edge 초기화
            node.add(new ArrayList<>());
        }
        for(int j=0; j<E; j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            node.get(u).add(new Integer[] {w, v});
        }

        pq.add(new Integer[] {0, K});

        while(!pq.isEmpty()){
            Integer[] curNode = pq.poll();

            for(int k=0; k<node.get(curNode[1]).size(); k++){
                if(dist[node.get(curNode[1]).get(k)[1]] > (curNode[0] + node.get(curNode[1]).get(k)[0])) {
                    dist[node.get(curNode[1]).get(k)[1]] = (curNode[0] + node.get(curNode[1]).get(k)[0]);
                    pq.add(new Integer[] {dist[node.get(curNode[1]).get(k)[1]], node.get(curNode[1]).get(k)[1]});
                }
            }
        }

        for(int l=1; l<dist.length; l++){
            if(dist[l] == Integer.MAX_VALUE){
                sb.append("INF");
            } else {
                sb.append(dist[l]);
            }
            if(l < dist.length - 1) sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        new Main().solution();
    }
}
