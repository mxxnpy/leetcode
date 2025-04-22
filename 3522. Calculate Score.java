public class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        boolean[] visited = new boolean[n];
        long score = 0;
        int i = 0;
        while (i >= 0 && i < n) {
            if (visited[i]) break;
            visited[i] = true;
            if (instructions[i].equals("add")) {
                score += values[i];
                i++;
            } else {
                i += values[i];
            }
        }
        return score;
    }
}