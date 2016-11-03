package myAlgorithm.graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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


}
