import java.util.*;

class Solution {
    private static final int MOD = 1000000007;
    
    public int idealArrays(int n, int maxValue) {
        // Pré-computar fatores primos
        int[] spf = sieve(maxValue);
        
        // Calcular coeficientes combinatórios
        int maxK = n + 20; // Ajustar conforme necessidade
        long[] fact = new long[maxK + 1];
        long[] invFact = new long[maxK + 1];
        precomputeFactorials(fact, invFact, maxK);
        
        // DP para contar sequências válidas
        long[] dp = new long[maxValue + 1];
        Arrays.fill(dp, 1);
        
        long total = 0;
        for (int i = 1; i <= maxValue; i++) {
            Map<Integer, Integer> factors = getPrimeFactors(i, spf);
            long count = 1;
            
            for (int exp : factors.values()) {
                count = count * comb(n + exp - 1, exp, fact, invFact) % MOD;
            }
            
            total = (total + count) % MOD;
        }
        
        return (int) total;
    }
    
    private int[] sieve(int n) {
        int[] spf = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                for (int j = i * 2; j <= n; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        return spf;
    }
    
    private Map<Integer, Integer> getPrimeFactors(int x, int[] spf) {
        Map<Integer, Integer> factors = new HashMap<>();
        while (x > 1) {
            int p = spf[x];
            while (x % p == 0) {
                factors.put(p, factors.getOrDefault(p, 0) + 1);
                x /= p;
            }
        }
        return factors;
    }
    
    private void precomputeFactorials(long[] fact, long[] invFact, int n) {
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        
        invFact[n] = pow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }
    
    private long comb(int n, int k, long[] fact, long[] invFact) {
        if (n < k || k < 0) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }
    
    private long pow(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
