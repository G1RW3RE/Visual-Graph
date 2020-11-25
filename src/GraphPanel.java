import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;

/**
 * @author ggloria
 */
public class GraphPanel extends JPanel {

    private Graph graph;
    private static final int MARGIN_LEFT = 20;
    private static final int MARGIN_TOP = 40;

    public GraphPanel() {
        super();
        graph = new Graph();
    }

    public void updateGraph() {
        Graphics2D g = (Graphics2D) getGraphics();
        int index = 0;
        for(Vertex v:graph.vertexes) {
            index++;
        }
    }
}
