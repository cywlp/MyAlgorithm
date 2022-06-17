package com.graph;

/**
 * @author :珠代
 * @description :自定义图的边
 * @create :2022-04-24 20:59:00
 */
public class Edge {
    public int weight; //权重
    public Node from;//开始节点
    public Node to;//结束节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
