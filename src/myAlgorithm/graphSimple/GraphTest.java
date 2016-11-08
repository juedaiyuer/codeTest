package myAlgorithm.graphSimple;

/**
 * Task:用队列Queue实现广度优先搜索
 *
 * Created by juedaiyuer on 16-11-7.
 */
public class GraphTest {
    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        GraphNode n5 = new GraphNode(5);

        n1.neighbors = new GraphNode[]{n2, n3, n5};
        n2.neighbors = new GraphNode[]{n1, n4};
        n3.neighbors = new GraphNode[]{n1, n4, n5};
        n4.neighbors = new GraphNode[]{n2, n3, n5};
        n5.neighbors = new GraphNode[]{n1, n3, n4};

        breathFirstSearch bfs = new breathFirstSearch(n1, 5);

    }

}
