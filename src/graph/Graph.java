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

    public boolean addVertex(Vertex vertex) {
        if (vertex == null) { return false; }
        if(!vertexes.contains(vertex)) {
            return vertexes.add(vertex);
        } else {
            return false;
        }
    }

    public Vertex getVertex(int key) {
        for(Vertex v : vertexes) {
            if(v.data.key == key) {
                return v;
            }
        }
        return null;
    }

    public int getSize() {
        return vertexes.size();
    }

}
