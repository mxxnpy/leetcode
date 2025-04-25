class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long result = 0;
        int lastMin = -1, lastMax = -1, leftBound = -1;
        
        for (int i = 0; i < nums.length; i++) {
            // Atualiza o limite esquerdo se elemento fora do intervalo
            if (nums[i] < minK || nums[i] > maxK) {
                leftBound = i;
            }
            // Atualiza últimas posições válidas
            if (nums[i] == minK) lastMin = i;
            if (nums[i] == maxK) lastMax = i;
            
            // Calcula a contribuição para subarrays válidos
            int validStart = Math.min(lastMin, lastMax);
            if (validStart > leftBound) {
                result += validStart - leftBound;
            }
        }
        return result;
    }
}