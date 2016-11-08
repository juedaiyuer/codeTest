package myAlgorithm.graphSimple;

/**
 * Created by juedaiyuer on 16-11-7.
 */
public class breathFirstSearch {

    public breathFirstSearch(GraphNode root, int x) {
        if (root.val == x) {
            System.out.println("find in root");
        }

        Queue queue = new Queue();
        root.visited = true;
        queue.enqueue(root);

        while (queue.first != null) {
            GraphNode c = (GraphNode) queue.IteratorQueue();
            for (GraphNode n : c.neighbors) {
                if (!n.visited) {
                    // 打印当前遍历节点
                    System.out.println(n + " ");
                    n.visited = true;
                    if (n.val == x)
                        System.out.println("Find " + n);

                    queue.enqueue(n);
                }
            }
        }


    }

}
