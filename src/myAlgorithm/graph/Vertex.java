package myAlgorithm.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 说明：图的顶点定义 Vertex
 *
 *
 * Created by juedaiyuer on 16-10-27.
 */
public class Vertex<T> implements VertexInterface<T>,java.io.Serializable {
    private T label;                             //标识标点，可以用不同类型来标识顶点，如String,Interger...
    private List<Edge> edgeList;                 //到该顶点临接点的边，实际上java.util.LinkedList存储
    private boolean visited;                     //标识顶点是否已访问
    private VertexInterface<T> previousVertex;   //该顶点的前驱顶点
    private double cost;                         //顶点的权值，与边的权值要区分开

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
    protected class Edge implements java.io.Serializable{
        private VertexInterface<T>  vertex;   //终点
        private double weight;                //权值

        //Vertex类本身就代表顶点对象，因此在这里只需提供endVertex，就可以表示一条边
        protected Edge(VertexInterface<T> endVertex , double edgeWeight){
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected VertexInterface<T> getEndVertex(){
            return vertex;
        }

        protected double getWeight(){
            return weight;
        }
    }

    /*
    * Task:遍历该顶点临接点的迭代器---为getNeighborInterator（）方法 提供迭代器
    * 1. 由于顶点的临接点以边的形式存储在java.util.List中，因此借助List的迭代器来实现
    * 2. 由于顶点的临接点由Edge类封装起来---见Edge.java的定义的第一个属性
    * 因此，首先获得遍历Edge对象的迭代器，再根据获得的Edge对象解析出临接点对象
    * */
    private class NeighborIterator implements Iterator<VertexInterface<T>>{
        Iterator<Edge> edgesIterator;
        private NeighborIterator(){
            edgesIterator = edgeList.iterator(); //获取LinkedList的迭代器
        }

        @Override
        public boolean hasNext(){
            return edgesIterator.hasNext();
        }

        @Override
        public VertexInterface<T> next(){
            VertexInterface<T> nextNeighbor = null;
            if (edgesIterator.hasNext()){
                Edge edgeToNextNeighbor = edgesIterator.next(); //LinkedList中存储的是Edge
                nextNeighbor = edgeToNextNeighbor.getEndVertex(); //从Edge对象中取出顶点
            }
            else {

                throw new NoSuchElementException();
            }
            return nextNeighbor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
    * Task:生成一个遍历该顶点所有临接边的权值的迭代器
    * 权值是Edge类的属性，因此先获得一个遍历Edge对象的迭代器，取得Edge对象在获取权值
    * */
    private class WeightIterator implements Iterator{
        private Iterator<Edge> edgesIterator;
        private WeightIterator(){
            edgesIterator = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edgesIterator.hasNext();
        }

        @Override
        public Object next() {
            Double result;
            if (edgesIterator.hasNext()){
                Edge edge = edgesIterator.next();
                result = edge.getWeight();
            }
            else
                throw new NoSuchElementException();
            return (Object)result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void visit() {
        this.visited = true;
    }

    @Override
    public void unVisit() {
        this.visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {

        // 将“边”（实质是顶点）插入顶点的临接表
        boolean result = false;
        // 顶点互不相同
        if(!this.equals(endVertex)){
            Iterator<VertexInterface<T>> neighbors = this.getNeighborInterator();
            boolean duplicateEdge = false;

            //保证不添加重复的边
            while (!duplicateEdge && neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)){
                    duplicateEdge = true;
                    break;
                }
                if(!duplicateEdge){
                    edgeList.add(new Edge(endVertex,edgeWeight));//添加一条新边
                    result = true;
                }
            }
        }

        return result;
    }


    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex,0);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborInterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator getWeightIterator() {
        return new WeightIterator();
    }

    @Override
    public boolean hasNeighbor() {
        return !(edgeList.isEmpty()); //临接点实质是存储在List
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborInterator();
        // 获取该顶点的第一个未被访问的临接点
        while (neighbors.hasNext() && result == null){
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited()){
                result = nextNeighbor;
            }
        }
        return result;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        cost = newCost;
    }

    @Override
    public double getCost() {
        return cost;
    }

    // 判断两个顶点是否相同
    public boolean equals(Object other){
        boolean result;
        if (other == null || (getClass() != other.getClass())) {
            result = false;
        }

        else
        {
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label); ////节点是否相同最终还是由标识 节点类型的类的equals() 决定

        }
        return result;
    }
}
