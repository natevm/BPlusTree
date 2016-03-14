/**
 * BPlusTreeGUI.java
 * 
 * Most of this code is automatically generated. 
 * Code that handles user interaction ends up here.
 * @author NateV
 */
package BPlusTreeDisplay;
import BPlusTree.BPlusTree;
import javax.swing.text.DefaultCaret;
import javax.swing.*;
import java.awt.Font;

public class BPlusTreeGUI extends javax.swing.JFrame {
    
    BPlusTree bPlusTree = new BPlusTree(5);
    int changeInKey = 1;

    public BPlusTreeGUI() {
        initComponents(); 
        DefaultCaret caret = (DefaultCaret)displayTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayTextPane = new javax.swing.JTextPane();
        btnAddKey = new javax.swing.JButton();
        spnrKey = new javax.swing.JSpinner();
        increaseFont = new javax.swing.JButton();
        decreaseFont = new javax.swing.JButton();
        newBPlusTree = new javax.swing.JButton();
        spnrOrder = new javax.swing.JSpinner();
        increaseKey = new javax.swing.JButton();
        decreaseKey = new javax.swing.JButton();
        spnrDeleteKey = new javax.swing.JSpinner();
        btnDeleteKey = new javax.swing.JButton();
        btnMonteCarlo = new javax.swing.JButton();
        lblIncrementorIndicator = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));

        displayTextPane.setEditable(false);
        displayTextPane.setBackground(new java.awt.Color(102, 102, 102));
        displayTextPane.setFont(new java.awt.Font("Courier", 1, 24)); // NOI18N
        displayTextPane.setForeground(new java.awt.Color(255, 255, 255));
        displayTextPane.setText("Your B+ Tree will appear here. If text becomes scrambled, change the font size.");
        jScrollPane1.setViewportView(displayTextPane);

        btnAddKey.setText("Add Key");
        btnAddKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKeyActionPerformed(evt);
            }
        });

        spnrKey.setModel(new javax.swing.SpinnerNumberModel(0, -100, 100, 1));

        increaseFont.setText("Increase Font");
        increaseFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseFontActionPerformed(evt);
            }
        });

        decreaseFont.setText("Decrease Font");
        decreaseFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseFontActionPerformed(evt);
            }
        });

        newBPlusTree.setText("New B+ Tree");
        newBPlusTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBPlusTreeActionPerformed(evt);
            }
        });

        spnrOrder.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(5), null, Integer.valueOf(1)));
        spnrOrder.setToolTipText("Order of the new b+ tree");

        increaseKey.setText("++");
        increaseKey.setToolTipText("Makes the Add Key shift the key up.");
        increaseKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseKeyActionPerformed(evt);
            }
        });

        decreaseKey.setText("--");
        decreaseKey.setToolTipText("Makes the Add Key shift the key down.");
        decreaseKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseKeyActionPerformed(evt);
            }
        });

        spnrDeleteKey.setModel(new javax.swing.SpinnerNumberModel(0, -100, 100, 1));

        btnDeleteKey.setText("Delete Key");
        btnDeleteKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKeyActionPerformed(evt);
            }
        });

        btnMonteCarlo.setText("Run Monte Carlo");
        btnMonteCarlo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonteCarloActionPerformed(evt);
            }
        });

        lblIncrementorIndicator.setText("Incrementor: 1");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spnrKey, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(increaseKey, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decreaseKey, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIncrementorIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnrDeleteKey, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteKey, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnrOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newBPlusTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonteCarlo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(increaseFont, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decreaseFont, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIncrementorIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(increaseKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spnrKey, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnrDeleteKey, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnrOrder)
                                .addComponent(newBPlusTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMonteCarlo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(increaseFont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(decreaseFont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeleteKey, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddKey, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnAddKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddKeyActionPerformed
        // TODO add your handling code here:
        bPlusTree.insert((int)spnrKey.getValue(), null);
        if ((int)spnrKey.getValue() >= 0)
        {spnrKey.setValue((int)spnrKey.getValue() + changeInKey);}
        displayTextPane.setText(bPlusTree.toString());
    }//GEN-LAST:event_btnAddKeyActionPerformed

    private void increaseFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFontActionPerformed
        // get the current font
        Font f = displayTextPane.getFont();

        // create a new, smaller font from the current font
        Font f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()+1);

        // set the new font in the editing area
        displayTextPane.setFont(f2);
    }//GEN-LAST:event_increaseFontActionPerformed

    private void decreaseFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseFontActionPerformed
         // get the current font
        Font f = displayTextPane.getFont();
        Font f2;
        if (f.getSize() > 5)
        // create a new, smaller font from the current font
            f2 = new Font(f.getFontName(), f.getStyle(), f.getSize()-1);
        else
            f2 = f;
        // set the new font in the editing area
        displayTextPane.setFont(f2);
    }//GEN-LAST:event_decreaseFontActionPerformed

    private void newBPlusTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBPlusTreeActionPerformed
        bPlusTree = new BPlusTree((int)spnrOrder.getValue());
        displayTextPane.setText("Your B+ Tree will appear here.");
    }//GEN-LAST:event_newBPlusTreeActionPerformed

    private void increaseKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseKeyActionPerformed
        // TODO add your handling code here:
        changeInKey++;
        lblIncrementorIndicator.setText("Incrementor: " + changeInKey);
    }//GEN-LAST:event_increaseKeyActionPerformed

    private void decreaseKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseKeyActionPerformed
        // TODO add your handling code here:
            changeInKey--;
            lblIncrementorIndicator.setText("Incrementor: " + changeInKey);
    }//GEN-LAST:event_decreaseKeyActionPerformed

    private void btnDeleteKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteKeyActionPerformed
        // TODO add your handling code here:
        bPlusTree.delete(Long.parseLong(spnrDeleteKey.getValue().toString()));
        displayTextPane.setText(bPlusTree.toString());
    }//GEN-LAST:event_btnDeleteKeyActionPerformed

    private void btnMonteCarloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonteCarloActionPerformed
        // TODO add your handling code here:
        JFrame frame = new JFrame();
        long maxValue = 0;
        int numberOfSimulations = 1;
        int deletionIndicator = 0;
        int numberOfInsertions = 0;
        int numberOfDeletions = 0;
//        do{ 
//            try{numberOfSimulations = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many times would you like the simulation to run?"));}
//            catch (Exception e) {numberOfSimulations = 0;}
//        }while (numberOfSimulations < 1);
        do{ 
            try{numberOfInsertions = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many keys should be inserted?"));}
            catch (Exception e) {numberOfInsertions = 0;}
        }while (numberOfInsertions < 1);
        do{ 
            try{maxValue = Long.parseLong(JOptionPane.showInputDialog(frame, "What is the max value that a key can be?\n(Must be > than # inserted keys)"));}
            catch (Exception e) {maxValue = 0;}
        }while (maxValue <= numberOfInsertions);
        do{ 
            try{deletionIndicator = Integer.parseInt(JOptionPane.showInputDialog(frame, "When should we delete values? (ex: every 100)"));}
            catch (Exception e) {deletionIndicator = 0;}
        }while (deletionIndicator < 1);
        do{ 
            try{numberOfDeletions = Integer.parseInt(JOptionPane.showInputDialog(frame, "When we delete values, how many should we delete?"));}
            catch (Exception e) {numberOfDeletions = 0;}
        }while (numberOfDeletions > deletionIndicator);
        
        bPlusTree.runMonteCarloSimulation(displayTextPane, numberOfSimulations, numberOfInsertions, 
                maxValue, deletionIndicator, numberOfDeletions);
        
//        bPlusTree.runInsertionHistory();
//        displayTextPane.setText(bPlusTree.toString());
    }//GEN-LAST:event_btnMonteCarloActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BPlusTreeGUI().setVisible(true);
                
            }
            
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKey;
    private javax.swing.JButton btnDeleteKey;
    private javax.swing.JButton btnMonteCarlo;
    private javax.swing.JButton decreaseFont;
    private javax.swing.JButton decreaseKey;
    private javax.swing.JTextPane displayTextPane;
    private javax.swing.JButton increaseFont;
    private javax.swing.JButton increaseKey;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblIncrementorIndicator;
    private javax.swing.JButton newBPlusTree;
    private javax.swing.JSpinner spnrDeleteKey;
    private javax.swing.JSpinner spnrKey;
    private javax.swing.JSpinner spnrOrder;
    // End of variables declaration//GEN-END:variables
}
