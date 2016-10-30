package myAlgorithm.graph;

/**
 * 说明：图接口定义
 * Created by juedaiyuer on 16-10-28.
 */
public interface BasicGraphInterface<T> {

    /*
    * Task:将给定顶点插入图
    * @param vertexLabel 标记新顶点的对象
    * */
    public void addVertex(T vertexLabel);
//    public boolean addVertex(T vertexLabel);

    /*
    * Task: 在图的指定顶点间插入一条加权边，这两个顶点之间插入边之前不能有边
    * @param begin 标识边的起点
    * @param end   标识边的终点
    * @return 插入成功返回true
    *
    * */
    public boolean addEdge(T begin,T end, double edgeWeight);

    public boolean addEdge(T begin,T end);

    /*
    * Task: 检查两个指定的顶点之间是否存在边
    * */
    public boolean hasEdge(T begin, T end);

    /*
    * Task: 检查图是否为空
    * */
    public boolean isEmpty();

    /*
    * Task: 获取图中顶点的个数
    * */
    public int getNumberOfVertices();

    /*
    * Task:获取图中边的条数
    * */
    public int getNumberOfEdges();

    /*
    * Task: 删除图中所有的顶点和边
    * */
    public void clear();

}
