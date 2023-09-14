
package console;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class NewJFrame extends javax.swing.JFrame {

    private List<JTextField> edgeInputFields;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        edgeInputFields = new ArrayList<>();
          
    }
      public boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
private boolean validateEdges(String edgeInput, int numNodes) {
    String[] edgeStrings = edgeInput.split(",");
    String[] uniqueEdges = new String[edgeStrings.length];

    for (String edgeString : edgeStrings) {
        String[] nodes = edgeString.trim().split("-");
        if (nodes.length == 2) {
            int node1 = Integer.parseInt(nodes[0].trim());
            int node2 = Integer.parseInt(nodes[1].trim());

           // if edge is valid
            if (node1 > numNodes || node2 > numNodes) {
                return false;
            }

            // if the edge is unique
            String edge = node1 + "-" + node2;
            for (String uniqueEdge : uniqueEdges) {
                if (edge.equals(uniqueEdge)) {
                    return false;
                }
            }

            // Add the edge to the array
            for (int i = 0; i < uniqueEdges.length; i++) {
                if (uniqueEdges[i] == null) {
                    uniqueEdges[i] = edge;
                    break;
                }
            }
        }
    }

    return true;
}
private static boolean isConnected(String edgeInput, int numNodes) {
    String[] edgeStrings = edgeInput.split(",");
    boolean[] visitedNodes = new boolean[numNodes + 1];

    for (String edgeString : edgeStrings) {
        String[] nodes = edgeString.trim().split("-");
        if (nodes.length == 2) {
            int node1 = Integer.parseInt(nodes[0].trim());
            int node2 = Integer.parseInt(nodes[1].trim());

            // Mark the nodes as visited
            visitedNodes[node1] = true;
            visitedNodes[node2] = true;
        }
    }

    // Check if all nodes have been visited
    for (int i = 0; i < numNodes; i++) {
        if (!visitedNodes[i]) {
            return false;
        }
    }

    return true;
}

    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        label1 = new java.awt.Label();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panel2 = new java.awt.Panel();
        GetStartedBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Demo");
        setBackground(new java.awt.Color(188, 188, 207));
        setMaximumSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(460, 400));

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));
        jDesktopPane1.setMaximumSize(new java.awt.Dimension(600, 600));
        jDesktopPane1.setName(""); // NOI18N
        jDesktopPane1.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel2.setText("   Minimum Spanning Tree Visualizer");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Inter", 1, 15)); // NOI18N
        jLabel1.setText("Design and Analysis of Algorithms Project");

        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GetStartedBtn.setBackground(new java.awt.Color(195, 200, 243));
        GetStartedBtn.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        GetStartedBtn.setText("Get Started");
        GetStartedBtn.setBorder(null);
        GetStartedBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GetStartedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetStartedBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel3.setText("Sara Ahmad Malik ");

        jLabel4.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel4.setText("Memoona Basharat");

        jLabel5.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel5.setText("Areeba Nazim");

        jLabel6.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel6.setText("21-CS-01");

        jLabel7.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel7.setText("21-CS-79");

        jLabel8.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel8.setText("21-CS-97");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Inter", 1, 15)); // NOI18N
        jLabel9.setText("Submitted to Mam Rabbia Mahum");

        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(panel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(GetStartedBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel9))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(GetStartedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GetStartedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GetStartedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetStartedBtnActionPerformed
    String input = JOptionPane.showInputDialog("Enter the number of nodes:");
        if (input == null) {
            return;
        }

        while (input.isEmpty() || !isNumeric(input)) {
            input = JOptionPane.showInputDialog("Invalid input! Please enter a valid number of nodes:");
            if (input == null) {
                return; // Exit if "Cancel" is clicked 
            }
        }
        int numNodes = Integer.parseInt(input);

        String edgeInput = JOptionPane.showInputDialog("Enter the edges (e.g., 1-2, 2-3, 3-4):");
        if (edgeInput == null) {
            return; // Exit the method if "Cancel" is clicked
        }

       //    while (edgeInput.matches("^[0-9]+-[0-9]+(,[0-9]+-[0-9]+)*$")) {
//        edgeInput = JOptionPane.showInputDialog("Invalid input! Please enter valid edges:");
//        if (edgeInput == null) {
//            return; // Exit the method if "Cancel" is clicked during the validation loop
//        }


    if (isConnected(edgeInput, numNodes)) {
        JOptionPane.showMessageDialog(null, "Invalid input! The entered edges do not form a connected graph.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!validateEdges(edgeInput, numNodes)) {
        JOptionPane.showMessageDialog(null, "Invalid input! The edge values should not exceed the number of nodes.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
        JPanel edgeInputPanel = new JPanel(new GridLayout(0, 2));

       
        String[] edgeStrings = edgeInput.split(",");
        for (String edgeString : edgeStrings) {
            
            String[] nodes = edgeString.trim().split("-");
            if (nodes.length == 2) {
                String node1 = nodes[0].trim();
                String node2 = nodes[1].trim();

                JTextField edgeField = new JTextField();
                JLabel edgeLabel = new JLabel(node1 + " - " + node2 + ": ");
                edgeInputPanel.add(edgeLabel);
                edgeInputPanel.add(edgeField);
                edgeInputFields.add(edgeField);
            }
        }

        int option = JOptionPane.showOptionDialog(null, edgeInputPanel, "Enter Weights corressponding edges",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            // Validate edge input values
            boolean isValid = true;
            for (JTextField edgeField : edgeInputFields) {
                String edgeValue = edgeField.getText().trim();
                if (!isNumeric(edgeValue)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
               int[] edgeValues = new int[edgeInputFields.size()];

                 for (int i = 0; i < edgeInputFields.size(); i++) {
                    JTextField edgeField = edgeInputFields.get(i);
                    String edgeValue = edgeField.getText().trim();
            int value = Integer.parseInt(edgeValue);
            edgeValues[i] = value;
                 }
             
                Visualizer panel2 = new Visualizer(edgeInput, numNodes, edgeValues);
                panel2.show();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid edge values! Please enter numeric values for all edges.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_GetStartedBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
  

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
               
                 
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GetStartedBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private java.awt.Label label1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
