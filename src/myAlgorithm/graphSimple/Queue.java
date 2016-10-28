package myAlgorithm.graphSimple;

/**
 * Created by juedaiyuer on 16-10-28.
 */
public class Queue {
    GraphNode first,last;

    public void enqueue(GraphNode n){
        if (first == null){
            first = n;
            last = first;
        }else {
            last.next = n;
            last = n;
        }
    }

}
