package baekjoon.Q1253;

/*
1. 아이디어
- 내림차순으로 정렬
- 현재 수를 제외한 left, right 포인터 값을 더한다
- 더한 값이 목표값과 같으면 true, 작으면 left+1, 그렇지 않으면 right-1
- 함수가 true이면 result+1
2. 변수
- int N
- int target
- int left
- int right
- int[] arr
3. 시간복잡도
- O(N^2)
 */

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = 0;
        Arrays.sort(arr);

        for(int i=0; i<N; i++){
            if(isGood(arr,i)){
                result++;
            }
        }

        System.out.print(result);
    }

    private static boolean isGood(int[] arr, int idx){
        int target = arr[idx];
        int left = 0, right = arr.length-1;

        while(left < right){
            if(left == idx){
                left++;
                continue;
            }
            if(right == idx){
                right--;
                continue;
            }

            int sum = arr[left] + arr[right];
            if(target == sum){
                return true;
            } else if(sum < target){
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
