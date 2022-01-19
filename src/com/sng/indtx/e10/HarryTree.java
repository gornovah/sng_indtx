package com.sng.indtx.e10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HarryTree {

    private static boolean[] visited;
    private static ArrayList[] links;

    public static int BFS(int start) {
        if (visited[start]) return 0;
        visited[start] = true;
        ArrayList<Integer> connections = links[start];
        int singleN = 1;
        for (Integer conn : connections) {
            if (conn < 0)
                singleN += BFS(-conn);
        }
        return singleN;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        links = new ArrayList[n + 1];
        if (sizeTreeSmallerThanThree(n)) return;
        for (int i = 0; i < n - 1; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            char color = in.next().charAt(0);
            if (links[v1] == null)
                links[v1] = new ArrayList<Integer>();
            if (links[v2] == null)
                links[v2] = new ArrayList<Integer>();
            links[v1].add(color == 'r' ? v2 : -v2);
            links[v2].add(color == 'r' ? v1 : -v1);
        }

        visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        long total = (long) n * (n - 1) * (n - 2) / 6;

        for (int i = 1; i <= n; i++) {
            int m = BFS(i);
            if (m == 2)
                total = total - n + 2;
            if (m >= 3) {
                long p1 = (long) m * (m - 1) * (m - 2) / 6;
                long p2 = (long) m * (m - 1) / 2 * (n - m);
                total = total - p1 - p2;
            }
        }
        System.out.println(total);
    }

    private static boolean sizeTreeSmallerThanThree(int n) {
        if (n < 3) {
            System.out.println("0");
            return true;
        }
        return false;
    }
}
