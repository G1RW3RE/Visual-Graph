package graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ggloria
 */
public class Vertex {

    public Elem data;
    public Deque<Vertex> edges;

    public Vertex(Elem elem) {
        this.data = elem;
        this.edges = new LinkedList<>();
    }

    public void linkTo(Vertex other) {
        if(other != null && !this.edges.contains(other)) {
            this.edges.add(other);
            other.edges.add(this);
        }
    }

    public void unlinkTo(Vertex other) {
        if(other != null) {
            this.edges.remove(other);
            other.edges.remove(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vertex) {
            return data.equals(((Vertex) obj).data);
        } else {
            return false;
        }
    }
}
