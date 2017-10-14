package kungfu.algdesign.graph;

/**
 * Created by lcsontos on 10/14/17.
 */
public class DirectedAcyclicGraph extends GraphImpl {

  public DirectedAcyclicGraph() {
    super();

    addEdge("a", "b");
    addEdge("a", "d");

    addEdge("b", "c");
    addEdge("b", "g");

    addEdge("d", "e");
    addEdge("d", "f");
    addEdge("d", "i");

    addEdge("e", "h");
    addEdge("e", "i");

    addEdge("f", "g");

    addEdge("g", "h");
  }

}
