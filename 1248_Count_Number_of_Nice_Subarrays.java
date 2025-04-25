import java.util.*;

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return countAtMostK(nums, k) - countAtMostK(nums, k - 1);
    }
    
    private int countAtMostK(int[] nums, int k) {
        int left = 0, countOdds = 0, total = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 != 0) {
                countOdds++;
            }
            
            while (countOdds > k) {
                if (nums[left] % 2 != 0) {
                    countOdds--;
                }
                left++;
            }
            
            total += right - left + 1;
        }
        return total;
    }
}