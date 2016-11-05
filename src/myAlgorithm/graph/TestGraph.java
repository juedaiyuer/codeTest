package myAlgorithm.graph;

/**
 * Created by juedaiyuer on 16-11-6.
 */
public class TestGraph {
    public static void main(String[] args) {
        GraphInterface<String> graph = new DirectedGraph<>();
        System.out.println("Graph is empty?" + graph.isEmpty());

        System.out.println("adding vertex...");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        System.out.println("number of graph's vertex = "+graph.getNumberOfVertices()); //5

        /*
		 *   <A,D>  <A,C>  <A,B>  <D,C>  <C,E>
		 */
    }
}
