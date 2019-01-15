/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import main.ClientTCP;
import main.Controller;
import main.ManagerServer;
import main.Message;
import main.Network;
import main.User;

/**
 *
 * @author katran
 */
public class Contact extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 1L;
    private ManagerServer server;
    private User user;
    private int port;
    private Controller contr;
    private Network network;
    private User destUser;
    private ArrayList<User> listContacts;

    /**
     * Creates new form Contact
     */
    public Contact(ManagerServer server, int port, User user, Controller contr) {
        initComponents();
        this.server=server;
        this.port=port;
        this.user=user;
        this.YourPseudo.setText("Pseudo : "+this.user.getPseudo());
        this.contr = contr;
        this.network = contr.getNetwork();
        this.listContacts = network.getListUsers();
        this.printContacts();
    }

    public void displayWindow() throws IOException{
       this.setVisible(true);

    }
    
    public void printContacts(){
        String s = "";
        for (User tmp : this.listContacts){
            s = tmp.getPseudo()+"\n"+s;
        }
        this.ListContacts.setText(s);
    }
    
    public void refreshContacts(){
        printContacts();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        ListContacts = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        pseudoConnected = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        disconnect = new javax.swing.JButton();
        YourPseudo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Online contacts");

        ListContacts.setEditable(false);
        ListContacts.setColumns(20);
        ListContacts.setRows(5);
        jScrollPane1.setViewportView(ListContacts);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Connect with :");

        pseudoConnected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pseudoConnectedActionPerformed(evt);
            }
        });

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        disconnect.setText("Disconnect");
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });

        YourPseudo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        YourPseudo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        YourPseudo.setText("Pseudo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(disconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(YourPseudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pseudoConnected, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connect, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(YourPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(connect, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(pseudoConnected))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
    	try {
           String pseudo = pseudoConnected.getText();
           if (pseudo.length()==0){
                 JOptionPane.showMessageDialog(new JFrame("Contact"), "Write a pseudo please");
           } else{
             if((destUser = network.findUserWithPseudo(pseudo))==null){
                 JOptionPane.showMessageDialog(new JFrame("Contact"), "Unknown pseudo");
             } else {
                 System.out.println(destUser.getPseudo());
                 ClientTCP c = new ClientTCP(this.destUser.getAddress(),1234,destUser,this.network);
                 c.sendData(new Message("Connect with "+ this.user.getPseudo(), this.user, destUser),true);
                 ChatWindow chat =new ChatWindow(c,this.user,destUser);
                 chat.displayWindow();
                 c.setChat(chat);
                 /* Print history */
                 chat.getWindowChatText().append(c.getHistory().printHistory());
                 Thread t = new Thread (c);
                 t.start();
             }
           }

        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(new JFrame("Contact"), "Invalid pseudo");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame("Contact"), "Invalid pseudo");
	}      
    }//GEN-LAST:event_connectActionPerformed

    private void pseudoConnectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pseudoConnectedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pseudoConnectedActionPerformed

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
            this.server.closeServer();
            this.setVisible(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.dispose();
            contr.getLogin().displayWindow();
        
    }//GEN-LAST:event_disconnectActionPerformed

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
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Contact().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ListContacts;
    private javax.swing.JLabel YourPseudo;
    private javax.swing.JButton connect;
    private javax.swing.JButton disconnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pseudoConnected;
    // End of variables declaration//GEN-END:variables
}
