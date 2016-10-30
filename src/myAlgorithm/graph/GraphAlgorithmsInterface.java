package myAlgorithm.graph;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by juedaiyuer on 16-10-30.
 */
public interface GraphAlgorithmsInterface<T> {

    /*
    * Task: 执行图的深层优先遍历
    * @param orgin 标识遍历的起点对象
    * @return 遍历顶点标识队列，起点的标识位于队列前端
    *
    * */
    public Queue<T> getDepthFirstTraversal(T origin);

    public Queue<T> getBreadthFirstTraversal(T origin);

    /*
    * Task:执行有向无环图的顶点的后拓扑排序
    * @return 由栈顶开始按拓扑有序排列的顶点标识栈
    * */
    public Stack<T> getTopologicalSort();

    /*
    * Task:寻找两个指定顶点之间的最短路径
    * @param begin 路径起点
    * @param end   路径终点
    * @param path  初始为空的栈
    *                       该栈保存沿最短路径的顶点
    *                       起点标识栈顶，终点表示栈底
    * @return       返回最短路径的长度
    *
    * */
    public int getShortestPath(T begin,T end,Stack<T> path);

    /*
    * Task:寻找两个指定顶点间费用最低的路径
    * @param begin
    * @param end
    * @param path
    *
    * */
    public double getCheapestPath(T begin,T end,Stack<T> path);

}
