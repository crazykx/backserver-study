package SingleNumber;

import java.util.HashSet;
import java.util.Set;

class Solution_2 {
    public int singleNumber(int[] nums) {
        Set<Integer> numsSet = new HashSet<Integer>();
        for(Integer num : nums) {
            if(numsSet.contains(num)){
                numsSet.remove(num);
            } else {
                numsSet.add(num);
            }
        }
        int res = 0;
        for(Integer num : numsSet) {
            res = num;
        }
        return res;
    }
}