import java.util.*;

class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (nums.get(i) % modulo == k) ? 1 : 0;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        int prefixSum = 0;
        long result = 0;

        for (int num : arr) {
            prefixSum += num;
            int target = (prefixSum - k) % modulo;
            if (target < 0) target += modulo;
            
            result += countMap.getOrDefault(target, 0);
            
            int currentKey = prefixSum % modulo;
            countMap.put(currentKey, countMap.getOrDefault(currentKey, 0) + 1);
        }

        return result;
    }
}