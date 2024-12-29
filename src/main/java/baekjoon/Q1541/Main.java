package baekjoon.Q1541;

/*
1. 아이디어
- "-" 나오기 전 까지 모든 수 더하고 "-" 나온 후 모든 수 빼기
- flag 값으로 minus 탐지여부 판단
- 숫자이면 numStr값에 저장
- 부호이면 result에 해당하는 연산 수행
2. 변수
- String exp
- String numStr
- int result
- boolean isMinus
3. 시간복잡도
- O(N)
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        int result = 0;
        StringBuilder numStr = new StringBuilder();
        boolean isMinus = false;

        for(int i=0; i <= exp.length(); i++){
            if(i < exp.length() && Character.isDigit(exp.charAt(i))){
                numStr.append(exp.charAt(i));
            } else {
                if(numStr.length() > 0){
                    result += isMinus ? -Integer.parseInt(numStr.toString()) : Integer.parseInt(numStr.toString());
                    numStr.setLength(0);
                }
                if(i < exp.length() && exp.charAt(i) == '-'){
                    isMinus = true;
                }
            }
        }

        System.out.println(result);

    }
}
