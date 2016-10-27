package myAlgorithm.graph;

import java.util.Iterator;

/**
 * Created by juedaiyuer on 16-10-27.
 *
 * source:https://github.com/hapjin/data-structures-and-abstraction-with-java/blob/master/graph/VertexInterface.java
 */
public interface VertexInterface<T> {
    /*
    * Task:取得顶点的标识---顶点标识用来区分各个顶点
    * @return 标记该顶点的对象
    * */

    public T getLabel();

    public void visit(); //标记该顶点已经被访问
    public void unVisit(); //标记该顶点尚未访问
    public boolean isVisited(); //判断顶点是否被访问

    /*
    * Task:用一条加权边连接该顶点与指定顶点
    *
    * @return 若插入成功，返回true
    * */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    public boolean connect(VertexInterface<T> endVertex);

    /*
    * Task:创建一个遍历器遍历从该顶点开始的所有边
    * @return 从该顶点开始的边对象的迭代器
    * */
    public Iterator<VertexInterface<T>> getNeighborInterator();

    /*
    * Task:创建一个迭代器，计算从该顶点到其临接点的边的权重
    * @return 该顶点的所有临接点的权重迭代器
    * */
    public Interator<Double> getWeightIterator();

    public boolean hasNeighbor(); //查看顶点是否最少有一个临接点



}
