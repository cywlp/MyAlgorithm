package com.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author :珠代
 * @description :自定义图结构
 * @create :2022-04-24 20:54:00
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
