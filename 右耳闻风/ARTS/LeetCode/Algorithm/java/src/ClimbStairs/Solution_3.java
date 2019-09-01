package ClimbStairs;

public class Solution_3 {

    public int climbStairs(int n) {
        if(n <= 2) {
            return n;
        }
        // n>2: 此时定义从 3 开始, 3 的前两步有 1 种走法
        int lastDecrTwo = 1;
        // n>2: 此时定义从 3 开始, 3 的前一步有 2 种走法
        int lastDecrOne = 2;
        for(int i = 3; i <= n; i++) {
            // n 每次走法就是 (n-1) 的走法 +  (n-2) 的走法
            int nowClimbNum = lastDecrTwo + lastDecrOne;
            // 计算完 n 的走法后, 将 n+1 的前两步走法更新为 n-1 的走法数
            lastDecrTwo = lastDecrOne;
            // 计算完 n 的走法后, 将 n+1 的前一步走法更新为 n 的走法数
            lastDecrOne = nowClimbNum;
        }
        return lastDecrOne;
    }

}
