package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ggloria
 */
public class Graph {

    public List<Vertex> vertexes;

    public Graph() {
        vertexes = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        if(vertex != null) {
            vertexes.add(vertex);
        }
    }

    public int getSize() {
        return vertexes.size();
    }

}
