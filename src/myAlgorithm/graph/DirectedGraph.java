package myAlgorithm.graph;

import java.util.*;

/**
 * Created by juedaiyuer on 16-10-30.
 */
public class DirectedGraph<T> implements GraphInterface<T>,java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // map对象用来保存图中的所有顶点，T是顶点标识，VertexInterface为顶点对象
    private Map<T,VertexInterface<T>> vertices;
    // 记录图中边的总数
    private int edgeCount;

    public DirectedGraph(){
        //按顶点的插入顺序保存顶点，这很重要，因为这会影响到后面图的遍历算法的正确性
        vertices = new LinkedHashMap<>();
    }

    @Override
    public void addVertex(T vertexLabel) {
        // 若顶点相同，新插入的顶点将覆盖原顶点，这是由linkedHashMap的put方法决定的
        // 每添加一个顶点，会创建一个LinkedList列表，它存储该顶点对应的临接点，或者说该顶点相关联的边
        vertices.put(vertexLabel,new Vertex(vertexLabel));
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if (beginVertex != null && endVertex != null){
            result = beginVertex.connect(endVertex,edgeWeight); // 起始点与终点连成一条边
        }

        if (result){
            edgeCount++;
        }

        return result;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin,end,0);
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T>  beginVertex = vertices.get(begin);
        VertexInterface<T>  endVertex = vertices.get(end);
        if (beginVertex == null || endVertex == null || beginVertex.hasNeighbor() == false){
            return found;
        }

        Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborInterator();
        while (!found && neighbors.hasNext()){
            VertexInterface<T> neighbor = neighbors.next();
            if (endVertex.equals(neighbor)){
                found = true;
            }
        }

        return found;
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    /*
    * 深度优先搜索
    * */
    public Queue<T> getDepthFirstTraversal(T origin) {
        resetVertices();
        LinkedList<VertexInterface<T>> vertexStack = new LinkedList<>(); //辅助DFS递归遍历
        Queue<T> traversalOrder = new LinkedList<>(); //保存DFS遍历顺序

        VertexInterface<T> originVertex = vertices.get(origin); //根据起始顶点的标识获得起始顶点
        originVertex.visit(); //访问起始顶点，起始顶点的出度不能为0（只考虑多于一个顶点的连通图），若为0,它就没有临接点了
        vertexStack.push(originVertex); // 各个顶点的入栈顺序就是DFS的遍历顺序
        traversalOrder.offer(originVertex.getLabel()); //每当一个顶点入栈时，就将它入队列，从而队列保存了整个遍历顺序

        while (!vertexStack.isEmpty()){
            VertexInterface<T> topVertex = vertexStack.peek();
            // 找到该顶点的一个未被访问的临接点，从该邻接点触发又去遍历邻接点的邻接点，也遍历了所有的边--复杂度O（E）
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
            if (nextNeighbor != null){
                nextNeighbor.visit();
                // 由于用的是if,在这里push邻接点后,下一次while循环pop的是该邻接点,然后又获得它的邻接点,---DFS
                vertexStack.push(nextNeighbor);
                traversalOrder.offer(nextNeighbor.getLabel());
            }else {
                // 当某顶点的所有邻接点都被访问了时,直接将该顶点pop,这样下一次while pop 时就回溯到前一个顶点
                vertexStack.pop();
            }
        }


        return traversalOrder;
    }

    @Override
    /*
    * 广度优先搜索
    * @param orgin 标识遍历的初始顶点
    * */
    public Queue<T> getBreadthFirstTraversal(T origin) {
        resetVertices();
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>(); //辅助DFS递归遍历,保存遍历过程中遇到的顶点，有出队列操作
        Queue<T> travelsalOrder = new LinkedList<>(); // 保存遍历过程中遇到的顶点，无出列操作
        VertexInterface<T> originVertex = vertices.get(origin); //根据顶点标识获得初始遍历顶点
        originVertex.visit(); //访问该顶点
        travelsalOrder.offer(originVertex.getLabel());
        vertexQueue.offer(originVertex);

        while (!vertexQueue.isEmpty()){
            VertexInterface<T> frontVertex = vertexQueue.poll(); //出队列
            Iterator<VertexInterface<T>> neigbors = frontVertex.getNeighborInterator();
            // 对于每一个顶点，都遍历它的邻接表，即遍历所有的边，复杂度为O（e）
            while (neigbors.hasNext()){
                VertexInterface<T> nextNeigbor = neigbors.next();
                if (!nextNeigbor.isVisited()){
                    nextNeigbor.visit(); //广度优先遍历未访问的点
                    travelsalOrder.offer(nextNeigbor.getLabel());
                    vertexQueue.offer(nextNeigbor);

                }
            }
        }

        return travelsalOrder;
    }

    @Override
    /*
    * Task:获得拓扑排序
    * 此算法的最坏时间复杂度为O(V*(V+E))==V * max{V,E}
    * */
    public Stack<T> getTopologicalSort() {
        resetVertices();

        Stack<T> vertexStack = new Stack<>(); //存放已访问的顶点的栈，该栈就是一个拓扑序列
        int numberOfVertices = vertices.size();

        for (int counter = 1 ; counter <= numberOfVertices ; counter++){
            VertexInterface<T> nextVertex = getNextTopologyOrder(); // 获得一个未被访问的且出度为0的顶点
            if (nextVertex != null){
                nextVertex.visit();
                vertexStack.push(nextVertex.getLabel()); //遍历完成后，出栈就可以获得图的一个拓扑序列
            }
        }

        return vertexStack;
    }

    /*
    * Task:获得一个未被访问的且出度为0的顶点
    * 最坏的复杂度为O(V+E)
    * */
    private VertexInterface<T> getNextTopologyOrder(){
        VertexInterface<T> nextVertex = null;
        Iterator<VertexInterface<T>> iterator = vertices.values().iterator(); // 获得图的顶点的迭代器
        boolean found = false;
        while (!found && iterator.hasNext()){
            nextVertex = iterator.next();
            if (nextVertex.isVisited() == false && nextVertex.getUnvisitedNeighbor() == null)
                found = true;
        }

        return nextVertex;
    }

    @Override
    public int getShortestPath(T begin, T end, Stack<T> path) {
        resetVertices();
        boolean done = false; // 标记整个遍历过程是否完成
        Queue< VertexInterface<T>> vertexQueue = new LinkedList<>();
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        beginVertex.visit();
        vertexQueue.offer(beginVertex);

        while (!done && !vertexQueue.isEmpty()){
            VertexInterface<T> frontVertex = vertexQueue.poll();
            Iterator<VertexInterface<T>> neigbors = frontVertex.getNeighborInterator();
            while (!done && neigbors.hasNext()){
                VertexInterface<T> nextNeighbor = neigbors.next();
                if (!nextNeighbor.isVisited()){
                    nextNeighbor.visit();
                    nextNeighbor.setPredecessor(frontVertex);
                    nextNeighbor.setCost(frontVertex.getCost()+1);
                    vertexQueue.offer(nextNeighbor);
                }

                if (nextNeighbor.equals(endVertex))
                    done = true;
            }
        }

        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()){
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }

        return pathLength;
    }

    @Override
    public double getCheapestPath(T begin, T end, Stack<T> path) {
        return 0;
    }

    //设置顶点的初始状态，时间复杂度为O（V）
    protected void resetVertices(){
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext()){
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unVisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);

        }
    }
}
