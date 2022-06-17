package com.graph;

import java.util.ArrayList;

/**
 * @author :珠代
 * @description :自定义图节点
 * @create :2022-04-24 20:56:00
 */
public class Node {
    public int value;
    public int in;//入度
    public int out;//出度
    public ArrayList<Node> nexts;//邻接节点
    public ArrayList<Edge> edges;//边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
