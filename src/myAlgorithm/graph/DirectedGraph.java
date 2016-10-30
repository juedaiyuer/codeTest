package myAlgorithm.graph;

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

}
