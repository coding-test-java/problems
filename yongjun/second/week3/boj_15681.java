package second.week3;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.StringTokenizer;




public class boj_15681 {

    static int[] parents, size;

    static ArrayList<Integer>[] tree, nodes;




    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();




        int n = Integer.parseInt(st.nextToken());

        int r = Integer.parseInt(st.nextToken());

        int q = Integer.parseInt(st.nextToken());




        parents = new int[n + 1];

        size = new int[n + 1];

        tree = new ArrayList[n + 1];

        nodes = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {

            tree[i] = new ArrayList<>();

            nodes[i] = new ArrayList<>();

        }




        while (n-- > 1) {

            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());

            int last = Integer.parseInt(st.nextToken());




            nodes[first].add(last);

            nodes[last].add(first);

        }

        makeTree(r, -1);

        for (int i = 0; i < q; i++) {

            int rootNode = Integer.parseInt(br.readLine());

            countSubTreeNodes(rootNode);




            sb.append(size[rootNode]).append("\n");

        }

        System.out.println(sb);

    }




    static void makeTree(int rootNode, int parent) {




        for (int node : nodes[rootNode]) {

            if (node != parent) {

                parents[node] = rootNode;

                tree[rootNode].add(node);

                makeTree(node, rootNode);

            }

        }

    }




    static void countSubTreeNodes(int currentNode) {

        size[currentNode] = 1;

        for (int node : tree[currentNode]) {

            if (size[node] != 0) {

                size[currentNode] += size[node];




            } else {

                countSubTreeNodes(node);

                size[currentNode] += size[node];

            }

        }

    }

}