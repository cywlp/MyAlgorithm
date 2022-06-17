package com.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-24 21:04:00
 */
public class MyGraph {

    /**
     * 转化为自定义的图 以【weight，from节点值，to节点值】为例
     *
     * @param matrix 给出的图的描述
     * @return 返回自定义的图
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            //将点传入自定义图的节点集合中
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //将边传入到自定义图的边集合中
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;//开始节点出度加1
            toNode.in++;//结束节点出度加1
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

    /**
     * 最小生成树  迪杰斯特拉算法（贪心）
     * @param graph 传进的图
     * @return 返回最小生成树
     */
    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        //那些点被解锁出来了，用set记录
        HashSet<Node> nodesSet = new HashSet<>();
        //那些边被解锁出来了，用set记录
        HashSet<Edge> edgesSet = new HashSet<>();

        Set<Edge> result = new HashSet<>();//返回结果，挑选的边
        for (Node node : graph.nodes.values()) {//任意一个点，用for循环是防止传入的图是森林
            //node为开始节点
            if (!nodesSet.contains(node)) {
                nodesSet.add(node);
                for (Edge edge : node.edges) { //由node点解锁的全部边
                    if (!edgesSet.contains(edge)) {
                        edgesSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); //弹出解锁的边中最小的边
                    Node toNode = edge.to; //可能是一个新节点
                    if (!nodesSet.contains(toNode)) { //set集合不包含则是新节点
                        nodesSet.add(node);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            if (!edgesSet.contains(nextEdge)) {
                                edgesSet.add(nextEdge);
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
