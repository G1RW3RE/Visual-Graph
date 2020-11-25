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
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {

    }
}
