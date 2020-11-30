import graph.Elem;

import javax.swing.*;

/**
 * @author ggloria
 */
public class GraphFrame extends JFrame {

    public static void main(String[] args) {
        new GraphFrame();
    }

    public GraphFrame() {
        super("图测试");
        setSize(1080, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        initializeComponents();
        setVisible(true);
    }

    private GraphPanel graphPanel;

    private void initializeComponents() {
        graphPanel = new GraphPanel();
        graphPanel.createGraph(new Elem[]{
                new Elem(5, "Jiujiang"),
                new Elem(4, "Wuhan"),
                new Elem(6, "Beijing"),
                new Elem(19, "Xi'an"),
                new Elem(21, "Guangzhou")
        },
        new int[][]{
                {5, 4},
                {5, 6},
                {5, 19},
                {21, 19}
        });
        setContentPane(graphPanel);
    }
}
