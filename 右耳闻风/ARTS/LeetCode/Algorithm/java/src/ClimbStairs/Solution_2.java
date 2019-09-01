package ClimbStairs;

import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    public Map<Integer, Integer> climbNums = new HashMap<>();

    public int climbStairs(int n) {
        if (!climbNums.containsKey(1) || !climbNums.containsKey(2)) {
            climbNums.put(1, 1);
            climbNums.put(2, 2);
        }

        if(climbNums.containsKey(n)) {
            return climbNums.get(n);
        }else{
            int climbNum = climbStairs(n - 1) + climbStairs(n - 2);
            climbNums.put(n, climbNum);
            return climbNum;
        }
    }
}
