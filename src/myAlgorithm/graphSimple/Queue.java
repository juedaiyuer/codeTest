package myAlgorithm.graphSimple;

/**
 * Created by juedaiyuer on 16-10-28.
 */
public class Queue {
    GraphNode first,last;

    /*
    * first last并没有先后之分，只不过是遍历节点的标志
    * */
    public void enqueue(GraphNode n) {
        if (first == null) {
            first = n;
            last = first;
        } else {
            last.next = n;
            last = n;
        }
    }

    /*
    * Task:遍历当前节点
    * */
    public GraphNode IteratorQueue() {
        if (first == null) {
            return null;
        } else {
            GraphNode temp = new GraphNode(first.val, first.neighbors);
            first = first.next;
            return temp;
        }
    }

}
