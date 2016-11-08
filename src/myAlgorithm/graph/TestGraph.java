package myAlgorithm.graph;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by juedaiyuer on 16-11-6.
 */
public class TestGraph {
    public static void main(String[] args) {
        GraphInterface<String> graph = new DirectedGraph<>();
        System.out.println("Graph is empty?" + graph.isEmpty());

        System.out.println("adding vertex...");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        System.out.println("number of graph's vertex = "+graph.getNumberOfVertices()); //5

        /*
		 *   <A,D>  <A,C>  <A,B>  <D,C>  <C,E>
		 */
        System.out.println("adding edges...");
        // 代码这里出现了问题

        graph.addEdge("A","D");
        graph.addEdge("A","C");
        graph.addEdge("A","B");
        graph.addEdge("D","C");
        graph.addEdge("C","E");
        System.out.println("number of graph's edge = "+graph.getNumberOfEdges());

        //判断两顶点是否存在边
        System.out.println("vertexs between B and C has Edge?"+graph.hasEdge("B","C"));
        System.out.println("vertexs between D and C has Edge?"+graph.hasEdge("D","C"));

        System.out.println("Breath First traverse graph with initial vertex 'A'...");
        Queue<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("A");
        while (!bfsTraversalOrder.isEmpty())
            System.out.println(bfsTraversalOrder.poll()+" " );

        System.out.println("\nDFS traverse graph with initial vertex 'A'...");
        Queue<String> dfsTraversalOrder = graph.getDepthFirstTraversal("A");
        while (!dfsTraversalOrder.isEmpty())
            System.out.println(dfsTraversalOrder.poll()+" ");

        System.out.println("\nTopological Order");
        Stack<String> stack = graph.getTopologicalSort();
        while (!stack.isEmpty())
            System.out.println(stack.pop()+" ");

        System.out.println("\ncleaning graph");
        graph.clear();
        System.out.println("now,number of vertexs ="+graph.getNumberOfVertices());


    }
}
