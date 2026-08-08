import java.util.*;

public class Solution {
    private static final int MOD = 1000000007;
    private int[] depth;
    private int[][] up;
    private int LOG;
    private List<List<Integer>> adj;
    private long[] powerOfTwo;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        // Deduced n from the edges array length since a tree has n - 1 edges
        int n = edges.length + 1;

        // 1. Initialize tree structure
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // 2. Setup Binary Lifting variables
        LOG = 0;
        while ((1 << LOG) <= n) {
            LOG++;
        }
        depth = new int[n + 1];
        up = new int[n + 1][LOG];

        // 3. Precompute powers of 2 modulo 10^9 + 7
        powerOfTwo = new long[n + 1];
        powerOfTwo[0] = 1;
        for (int i = 1; i <= n; i++) {
            powerOfTwo[i] = (powerOfTwo[i - 1] * 2) % MOD;
        }

        // 4. Run DFS to populate depths and immediate parents (up[i][0])
        dfs(1, 1, 0);

        // 5. Build Binary Lifting Table
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // 6. Process Queries
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
            } else {
                int lca = getLCA(u, v);
                int k = depth[u] + depth[v] - 2 * depth[lca];
                ans[i] = (int) powerOfTwo[k - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent, int d) {
        depth[node] = d;
        up[node][0] = parent;
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, d + 1);
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Lift u to the same depth as v
        for (int j = LOG - 1; j >= 0; j--) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        // Lift both together right below their common ancestor
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }
}