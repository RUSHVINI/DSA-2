public class UberSegmentTree {

    static int n = 16;
    static double[] tree = new double[4 * n];
    static double[] lazy = new double[4 * n];
    static double[] zones = new double[n];

    static void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = zones[start];
        } else {

            int mid = (start + end) / 2;

            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node] = Math.max(tree[2 * node],
                    tree[2 * node + 1]);
        }
    }

    static void update(int node, int start, int end,
                       int l, int r, double value) {

        if (lazy[node] != 0) {

            tree[node] += lazy[node];

            if (start != end) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (start > r || end < l)
            return;

        if (start >= l && end <= r) {

            tree[node] += value;

            if (start != end) {
                lazy[2 * node] += value;
                lazy[2 * node + 1] += value;
            }
            return;
        }

        int mid = (start + end) / 2;

        update(2 * node, start, mid, l, r, value);
        update(2 * node + 1, mid + 1, end, l, r, value);

        tree[node] = Math.max(tree[2 * node],
                tree[2 * node + 1]);
    }

    static double query(int node, int start, int end,
                        int l, int r) {

        if (lazy[node] != 0) {

            tree[node] += lazy[node];

            if (start != end) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }

            lazy[node] = 0;
        }

        if (start > r || end < l)
            return Double.MIN_VALUE;

        if (start >= l && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        double left =
                query(2 * node, start, mid, l, r);

        double right =
                query(2 * node + 1, mid + 1, end, l, r);

        return Math.max(left, right);
    }

    public static void main(String[] args) {

        for (int i = 0; i < n; i++)
            zones[i] = 1.0;

        build(1, 0, n - 1);

        System.out.println("====================================");
        System.out.println("      UBER SURGE MANAGEMENT");
        System.out.println("====================================");

        update(1, 0, n - 1, 3, 9, 0.5);
        System.out.println("\nUpdate [3,9] += 0.5 Applied");

        update(1, 0, n - 1, 7, 14, 0.3);
        System.out.println("Update [7,14] += 0.3 Applied");

        double max1 =
                query(1, 0, n - 1, 0, 15);

        System.out.println("\nMaximum Surge [0,15] = "
                + max1);

        update(1, 0, n - 1, 2, 6, 0.7);
        System.out.println("\nUpdate [2,6] += 0.7 Applied");

        double max2 =
                query(1, 0, n - 1, 4, 10);

        System.out.println("Maximum Surge [4,10] = "
                + max2);
    }
}