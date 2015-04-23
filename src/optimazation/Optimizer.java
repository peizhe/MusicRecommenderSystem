package optimazation;

public class Optimizer {
    public void blxAlphaCrossov1er(String[] a, String[] b) {
        for (int i = -0; i < a.length; i++) {
            int x = Integer.parseInt(a[i]);
            int y = Integer.parseInt(b[i]);
            int distance = Math.abs(x - y);
            int min = Math.min(x, y);
            int max = Math.max(x, y);
            int u = 0;
            for (int j = min; j <= max; j++) {
                u += distance;
            }
            u /= (max - min);
            a[i + 1] = "" + u;
            a[i + 1] = "" + u;
        }
    }
}
