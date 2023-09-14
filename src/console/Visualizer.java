/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package console;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Stroke;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */


public class Visualizer extends javax.swing.JFrame {

    /**
     * Creates new form Visualizer
     */
class Node {
    public int number;
    public int x;
    public int y;

    public Node(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }
}

class Edge {
    public Node start;
    public Node end;
    public int weight;

    public Edge(Node start, Node end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    Node getStart(){
        return start;
    }
    
    Node getEnd(){
        return end;
    }
}

class DisjointSet {
    private Map<Node, Node> parentMap;
    private Map<Node, Integer> rankMap;

    public DisjointSet() {
        parentMap = new HashMap<>();
        rankMap = new HashMap<>();
    }

    public void makeSet(Node node) {
        parentMap.put(node, node);
        rankMap.put(node, 0);
    }

    public Node find(Node node) {
        Node parent = parentMap.get(node);
        if (parent == node) {
            return node;
        }
        Node newParent = find(parent);
        parentMap.put(node, newParent);
        return newParent;
    }

    public void union(Node node1, Node node2) {
        Node root1 = find(node1);
        Node root2 = find(node2);

        if (root1 == root2) {
            return;
        }

        int rank1 = rankMap.get(root1);
        int rank2 = rankMap.get(root2);

        if (rank1 < rank2) {
            parentMap.put(root1, root2);
        } else if (rank1 > rank2) {
            parentMap.put(root2, root1);
        } else {
            parentMap.put(root1, root2);
            rankMap.put(root2, rank2 + 1);
        }
    }
}

class GraphPanel extends JPanel {
    private List<Node> nodes;
    private List<Edge> edges;
    private int nodeRadius;
private List<Edge> highlightedEdge;
    public GraphPanel(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.nodeRadius = 15;
    }
     public void highlightEdges(List<Edge> highlightedEdges) {
        this.highlightedEdge = highlightedEdges;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

       
        Stroke oldStroke = g2d.getStroke();
        Stroke newStroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(newStroke);

        // Draw edges and weights
        for (Edge edge : edges) {
            Node start = edge.start;
            Node end = edge.end;
            int weight = edge.weight;
            
            if(highlightedEdge != null){
             // Check if the edge is in the MST (highlighted)
            if (highlightedEdge.contains(edge)) {
                g.setColor(Color.GREEN); 
            } else {
                g.setColor(Color.RED); 
            }
            }
            else{
                g.setColor(Color.RED);
            }

            // Calculate the positions of the nodes and the angle between them
            int startX = start.x + (int) (nodeRadius * Math.cos(Math.atan2(end.y - start.y, end.x - start.x)));
            int startY = start.y + (int) (nodeRadius * Math.sin(Math.atan2(end.y - start.y, end.x - start.x)));
            int endX = end.x + (int) (nodeRadius * Math.cos(Math.atan2(start.y - end.y, start.x - end.x)));
            int endY = end.y + (int) (nodeRadius * Math.sin(Math.atan2(start.y - end.y, start.x - end.x)));

    // Adjust the positions to prevent overlap with nodes
            if (startX == endX && startY == endY) {
                startX += nodeRadius;
                endX -= nodeRadius;
            }

            g2d.drawLine(startX, startY, endX, endY);

            // Calculate the weight label position
            int weightX = (startX + endX) / 2;
            int weightY = (startY + endY) / 2;

            // Draw weight label
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Inter", Font.BOLD, 14));
            g2d.drawString(Integer.toString(edge.weight), weightX, weightY);
        }
        
        if(highlightedEdge != null){
             g2d.setColor(Color.GREEN);
        }
        else{
             g2d.setColor(Color.BLUE);
        }

        // Draw nodes
        for (Node node : nodes) {
           
            int nodeX = node.x - nodeRadius;
            int nodeY = node.y - nodeRadius;
            int nodeWidth = 2 * nodeRadius;
            int nodeHeight = 2 * nodeRadius;
            g2d.drawOval(nodeX, nodeY, nodeWidth, nodeHeight);
            g2d.setFont(new Font("Inter", Font.BOLD, 14));
            g2d.drawString(Integer.toString(node.number), node.x - 5, node.y + 5);
        }
        
      clearTable();
        
        for(Edge edge: edges){
           addEdgeToTable(edge);
        }
        
        g2d.setStroke(oldStroke);
    }
}
 
    private List<Node> nodes;
    private List<Edge> edges;
    private int numNodes;
    private GraphPanel graphPanel;
  private DefaultTableModel tableModel;
    public Visualizer() {
        initComponents();
    }

    public Visualizer(String edge, int num, int[] weights) {
        initComponents();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        numNodes = num;
        tableModel = (DefaultTableModel) jTable1.getModel();

    Random random = new Random();
    for (int i = 0; i < numNodes; i++) {
        int x = random.nextInt(this.jLayeredPane1.getWidth() - 100) + 50;
        int y = random.nextInt(this.jLayeredPane1.getHeight() - 100) + 50;
        nodes.add(new Node(i + 1, x, y));
    }

        String[] edgeStrings = edge.split(",");
        int i = 0;
        for (String edgeString : edgeStrings) {
            String[] nodesString = edgeString.split("-");
            int startNode = Integer.parseInt(nodesString[0].trim());
            int endNode = Integer.parseInt(nodesString[1].trim());
            edges.add(new Edge(nodes.get(startNode - 1), nodes.get(endNode - 1), weights[i]));
            i++;
        }
        
         repaint();

        graphPanel = new GraphPanel(nodes, edges);
        graphPanel.setBounds(0, 0, jLayeredPane1.getWidth(), jLayeredPane1.getHeight());
        jLayeredPane1.add(graphPanel);
        
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        mst_Label = new javax.swing.JLabel();
        label_mst = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MST Visualizer");
        setPreferredSize(new java.awt.Dimension(890, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(890, 650));

        jLabel1.setFont(new java.awt.Font("Inter", 1, 20)); // NOI18N
        jLabel1.setText("Minimum Spanning Tree Visualizer");

        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setText("Using Kruskals Algorithm");

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(850, 600));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 692, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jButton1.setText("Run Kruskals");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Edges Pairs", "Weights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel3.setText("Original Graph Table");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Edges Pairs", "Weights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel4.setText("MST Graph Table");

        mst_Label.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N

        label_mst.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        label_mst.setName("label_mst"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 658, Short.MAX_VALUE)
                .addComponent(mst_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(262, 262, 262)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(319, 319, 319)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_mst, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(13, 13, 13)
                        .addComponent(label_mst, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mst_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLayeredPane1.getAccessibleContext().setAccessibleName("GraphArea");
        jButton1.getAccessibleContext().setAccessibleName("RunAlgo");
        mst_Label.getAccessibleContext().setAccessibleName("mst");
        label_mst.getAccessibleContext().setAccessibleName("label_mst");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 private void addEdgeToTable(Edge edge) {
        Node start = edge.getStart();
        Node end = edge.getEnd();
        String edgePair = "(" + start.number + ", " + end.number + ")";
        int weight = edge.weight;
        tableModel.addRow(new Object[]{edgePair, weight});
    }

private void fillMSTTable(List<Edge> mstEdges) {
    DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
    model2.setRowCount(0); 

    for (Edge edge : mstEdges) {
        Node source = edge.start;
        Node destination = edge.end;
        int weight = edge.weight;
  String edgePair = "(" + source.number + ", " + destination.number + ")";
       
        model2.addRow(new Object[]{edgePair, weight});
    }
}



    private void clearTable() {
        tableModel.setRowCount(0);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        runKruskals();
    }//GEN-LAST:event_jButton1ActionPerformed


 private void runKruskals() {
    List<Edge> mst = new ArrayList<>(); 
    DisjointSet disjointSet = new DisjointSet(); 
    int totalCost = 0;

    for (Node node : nodes) {
        disjointSet.makeSet(node);
    }

    Collections.sort(edges, Comparator.comparingInt(edge -> edge.weight));

    for (Edge edge : edges) {
        Node start = edge.start;
        Node end = edge.end;

        if (!disjointSet.find(start).equals(disjointSet.find(end))) {
            disjointSet.union(start, end);
            mst.add(edge);
            totalCost += edge.weight;
        }

        // Visualize mst at each step
        graphPanel.highlightEdges(mst);
        graphPanel.repaint();

        // Delay
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   
    graphPanel.highlightEdges(mst);
    graphPanel.repaint();
    fillMSTTable(graphPanel.highlightedEdge);

  
    StringBuilder mstWeights = new StringBuilder();
    for (Edge edge : mst) {
        mstWeights.append("+").append(edge.weight);
    }
    label_mst.setText("Total Cost: " + mstWeights.substring(1) + " = " + totalCost);

    JOptionPane.showMessageDialog(null, "Kruskal Algorithm is applied. The highlighted graph is the Minimum Spanning Tree");
}

    
    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(Visualizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(Visualizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(Visualizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Visualizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
        new Visualizer().setVisible(true);
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel label_mst;
    private javax.swing.JLabel mst_Label;
    // End of variables declaration//GEN-END:variables
}
