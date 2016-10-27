package myAlgorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juedaiyuer on 16-10-27.
 */
public class Vertex<T> implements VertexInterface<T>,java.io.Serializable {
    private T label; //标识标点，可以用不同类型来标识顶点，如String,Interger...
    private List<Edge> edgeList; //到该顶点临接点的边，实际上java.util.LinkedList存储
    private boolean visited; //标识顶点是否已访问
    private VertexInterface<T> previousVertex; //该顶点的前驱顶点
    private double cost; //顶点的权值，与边的权值要区分开

    public Vertex(T vertexLabel){
        label = vertexLabel;
        edgeList = new LinkedList<Edge>(); //是Vertex的属性，说明每个顶点都有一个edgeList用来存储所有与该顶点有关系的边
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    /*
    * Task:单独的类表示边，主要是考虑带权值的边
    * 1. Edge类封装了一个顶点和一个double类型变量
    * 2. 若不需要考虑权值，可以不需要单独创建一个Edge类来表示边，只需要一个保存顶点的列表即可
    * */
}
