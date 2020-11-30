import graph.Elem;
import graph.Graph;
import graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * @author ggloria
 */
public class GraphPanel extends JPanel {

    private Graph graph;

    public GraphPanel() {
        super();
        graph = new Graph();
    }

    /** GUI Constants */
    private static final int BORDER_LEFT = 50;
    private static final int BORDER_TOP = 20;
    private static final int MARGIN_TOP = 5;
    private static final int MARGIN_LEFT = 24;

    private final static int VERTEX_WIDTH = 300;
    private final static int VERTEX_HEIGHT = 55;
    private final static int VERTEX_DATA_OFF = 60;
    private final static int VERTEX_PTR_OFF = 240;
    private final static int VERTEX_STR_X_OFF = 20;
    private final static int VERTEX_STR_Y_OFF = 36;
    private final static int VERTEX_INDEX_X_OFF = - 20;
    private final static int VERTEX_INDEX_Y_OFF = 36;
    private final static int VERTEX_LINK_X_OFF = VERTEX_PTR_OFF + 30;
    private final static int VERTEX_LINK_Y_OFF = VERTEX_HEIGHT / 2;

    private final static int EDGE_Y_OFF = 2;
    private final static int EDGE_HEIGHT = 50;
    private final static int EDGE_WIDTH = 100;
    private final static int EDGE_PTR_OFF = 50;
    private final static int EDGE_STR_X_OFF = 20;
    private final static int EDGE_STR_Y_OFF = 33;
    private final static int EDGE_LINK_X_OFF = EDGE_PTR_OFF + 24;
    private final static int EDGE_LINK_Y_OFF = EDGE_HEIGHT / 2;

    private final static int VG_MARGIN_TOP = 40;
    private final static int VG_CENTER_X_OFF = 200;
    private final static int VG_CENTER_Y_OFF = 160;
    private final static int VG_ORBIT_RADIUS = 120;
    private final static int VG_VERTEX_RADIUS = 80;
    private final static int VG_STR_X_OFF = 32;
    private final static int VG_STR_Y_OFF = 46;
    private final static double VG_90 = Math.PI / 2;

    private int WIN_WIDTH;
    private int WIN_HEIGHT;

    private final static Color COLOR_VERTEX_FILL = new Color(0xC2C2C2);
    private final static Color COLOR_VERTEX_BORDER = Color.BLACK;
    private final static Color COLOR_TEXT = Color.BLACK;
    private final static Color COLOR_INDEX = new Color(0x0701B1);
    private final static Color COLOR_LINK_LINE = new Color(0x805100);

    private final static Font FONT_TEXT = new Font("等线", Font.PLAIN, 24);

    private final static BasicStroke STOKE_LINE = new BasicStroke(1.6f);

    /** GUI */
    public void drawGraph(Graphics graphics) {
        if(graph == null) { return; }
        Graphics2D g = (Graphics2D) graphics;
        g.setStroke(STOKE_LINE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(FONT_TEXT);
        /* 清空区域 */
        g.clearRect(0, 0, getWidth(), getHeight());
        int index = 0;
        int vx, vy;
        int vertexCount = graph.getSize();
        /* 可视化邻接表 */
        for(Vertex vertex : graph.vertexes) {
            index++;
            vx = BORDER_LEFT;
            vy = BORDER_TOP + (index-1) * VERTEX_HEIGHT + index * MARGIN_TOP;
            /* 序号 */
            g.setColor(COLOR_INDEX);
            g.drawString("" + (index-1), vx + VERTEX_INDEX_X_OFF, vy + VERTEX_INDEX_Y_OFF);
            /* 头节点 */
            g.setColor(COLOR_VERTEX_FILL);
            g.fillRoundRect(vx, vy, VERTEX_WIDTH, VERTEX_HEIGHT, 10, 10);
            g.setColor(COLOR_VERTEX_BORDER);
            g.drawRoundRect(vx, vy, VERTEX_WIDTH, VERTEX_HEIGHT, 10, 10);
            g.drawLine(vx + VERTEX_DATA_OFF, vy, vx + VERTEX_DATA_OFF, vy + VERTEX_HEIGHT);
            g.drawLine(vx + VERTEX_PTR_OFF, vy, vx + VERTEX_PTR_OFF, vy + VERTEX_HEIGHT);
            g.setColor(COLOR_TEXT);
            g.drawString("" + vertex.data.key, vx + VERTEX_STR_X_OFF, vy + VERTEX_STR_Y_OFF);
            g.drawString("" + vertex.data.others, vx + VERTEX_DATA_OFF + VERTEX_STR_X_OFF, vy + VERTEX_STR_Y_OFF);
            if(vertex.edges.isEmpty()) {
                g.drawString("∧", vx + VERTEX_PTR_OFF + VERTEX_STR_X_OFF, vy + VERTEX_STR_Y_OFF);
            } else {
                g.setColor(COLOR_LINK_LINE);
                g.drawLine(vx + VERTEX_LINK_X_OFF, vy + VERTEX_LINK_Y_OFF, vx + VERTEX_WIDTH + MARGIN_LEFT, vy + VERTEX_LINK_Y_OFF);
            }
            /* 链表 */
            int lvx, lvy = vy + EDGE_Y_OFF;
            int lIndex = 0;
            Iterator<Vertex> iterator = vertex.edges.iterator();
            while(iterator.hasNext()) {
                lIndex++;
                Vertex edgeVertex = iterator.next();
                lvx = vx + VERTEX_WIDTH + (lIndex-1) * EDGE_WIDTH + lIndex * MARGIN_LEFT;
                g.setColor(COLOR_VERTEX_FILL);
                g.fillRoundRect(lvx, lvy, EDGE_WIDTH, EDGE_HEIGHT, 10, 10);
                g.setColor(COLOR_VERTEX_BORDER);
                g.drawRoundRect(lvx, lvy, EDGE_WIDTH, EDGE_HEIGHT, 10, 10);
                g.drawLine(lvx + EDGE_PTR_OFF, lvy, lvx + EDGE_PTR_OFF, lvy + EDGE_HEIGHT);
                g.setColor(COLOR_TEXT);
                g.drawString("" + graph.vertexes.indexOf(edgeVertex), lvx + EDGE_STR_X_OFF, lvy + EDGE_STR_Y_OFF);
                if(!iterator.hasNext()) {
                    g.drawString("∧", lvx + EDGE_PTR_OFF + EDGE_STR_X_OFF, lvy + EDGE_STR_Y_OFF);
                } else {
                    g.setColor(COLOR_LINK_LINE);
                    g.drawLine(lvx + EDGE_LINK_X_OFF, lvy + EDGE_LINK_Y_OFF, lvx + EDGE_WIDTH + MARGIN_LEFT, lvy + EDGE_LINK_Y_OFF);
                }
            }
        }
        /* 可视化图节点 */
        int vgXOff = BORDER_LEFT;
        int vgYOff = BORDER_TOP + vertexCount * (VERTEX_HEIGHT + MARGIN_TOP) + VG_MARGIN_TOP;
        double theta = 2 * Math.PI / vertexCount;
        int vgx, vgy;
        int [][] vgCords = new int[vertexCount][2];
        /* 计算坐标 */
        index = 0;
        for(Vertex vertex : graph.vertexes) {
            vgCords[index][0] = vgXOff + VG_CENTER_X_OFF + Math.round(((float) Math.cos(theta * index - VG_90) * VG_ORBIT_RADIUS)) - VG_VERTEX_RADIUS / 2;
            vgCords[index][1] = vgYOff + VG_CENTER_Y_OFF + Math.round(((float) Math.sin(theta * index - VG_90) * VG_ORBIT_RADIUS)) - VG_VERTEX_RADIUS / 2;
            index++;
        }
        /* 绘制 */
        index = 0;
        for(Vertex vertex : graph.vertexes) {
            vgx = vgCords[index][0];
            vgy = vgCords[index][1];
            /* 绘制边 */
            g.setColor(COLOR_LINK_LINE);
            for(Vertex edgeVertex : vertex.edges) {
                int eIndex = graph.vertexes.indexOf(edgeVertex);
                // 避免重复绘制
                if(eIndex > index) {
                    g.drawLine(vgx + VG_VERTEX_RADIUS / 2, vgy + VG_VERTEX_RADIUS / 2, vgCords[eIndex][0] + VG_VERTEX_RADIUS / 2, vgCords[eIndex][1] + VG_VERTEX_RADIUS / 2);
                }
            }
            /* 绘制节点 */
            g.setColor(COLOR_VERTEX_FILL);
            g.fillOval(vgx, vgy, VG_VERTEX_RADIUS, VG_VERTEX_RADIUS);
            g.setColor(COLOR_VERTEX_BORDER);
            g.drawOval(vgx, vgy, VG_VERTEX_RADIUS, VG_VERTEX_RADIUS);
            g.setColor(COLOR_TEXT);
            g.drawString("" + index, vgx + VG_STR_X_OFF, vgy + VG_STR_Y_OFF);
            index++;
        }
    }

    /** 强制手动刷新画面 */
    public void drawGraph() {
        if(graph == null || getGraphics() == null) { return; }
        drawGraph(getGraphics());
    }

    public void createEmptyGraph() {
        graph = new Graph();
    }

    private int currentSelection = -1;
    public void selectVertex(int index) {
        if(currentSelection == -1) {

        }
    }

    public boolean createGraph(Elem[] elems, int[][] edges) {
        if(elems == null || edges == null) { return false; }
        // 构建顶点集合
        boolean flag = true;
        for(Elem elem : elems) {
            flag &= graph.addVertex(new Vertex(elem));
        }
        if(!flag) {
            graph = new Graph();
            return false;
        }
        // 构建边集合
        Vertex v1, v2;
        for(int[] edge : edges) {
            assert edge.length == 2;
            v1 = graph.getVertex(edge[0]);
            v2 = graph.getVertex(edge[1]);
            if(v1 == null || v2 == null) {
                graph = new Graph();
                return false;
            } else {
                v1.linkTo(v2);
                v2.linkTo(v1);
            }
        }
        drawGraph();
        return true;
    }

    public void destroyGraph() {
        graph = null;
    }

    /** 自动刷新 */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGraph(g);
    }
}
