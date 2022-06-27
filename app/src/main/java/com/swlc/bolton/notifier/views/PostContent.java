/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.swlc.bolton.notifier.views;

import com.swlc.bolton.notifier.dto.UserDTO;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author athukorala
 */
public class PostContent extends javax.swing.JFrame {

    // for draggable
    int xMouse;
    int yMouse;

    //
    private UserDTO loggedUserObj;

    /**
     * Creates new form PostContent
     */
    public PostContent() {
        initComponents();
        setSize(750, 600);
        showTime();
    }

    public PostContent(UserDTO userDTO) {
        this.loggedUserObj = userDTO;
        initComponents();
        setLocationRelativeTo(null);
        setSize(750, 600);
        showTime();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width, 0);
//        setLocationRelativeTo(null);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
//        this.setLocation(dim.width, dim.height / 2 - this.getSize().height / 2);

        // session details
        setSessionDetails();
        // underline back button text
        btnBackToHome.setText("<html><u>Back to Home</u></html>");
    }

    private void draggableWindow(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void showTime() {
        Timer tTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                Calendar cal = new GregorianCalendar();
                Date d1 = new Date(); //java.util.Date ->get Current date and time
                SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy  hh:mm a");
                String formatDate = df.format(d1);
                txtDate.setText(formatDate);
            }
        });
        tTimer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        lblSWLCImg = new javax.swing.JLabel();
        lblBolImg = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JLabel();
        separatorHrz = new javax.swing.JSeparator();
        lblCopyright = new javax.swing.JLabel();
        lblDevelopedTxt = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        btnBackToHome = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        spPostWrapper = new javax.swing.JScrollPane();
        txtPost = new javax.swing.JTextPane();
        btnPost = new javax.swing.JButton();
        lblBackgroundImg = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(750, 600));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setPreferredSize(new java.awt.Dimension(750, 600));
        mainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mainPanelMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mainPanelMouseDragged(evt);
            }
        });

        lblSWLCImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logos/swlc_logo.png"))); // NOI18N
        lblSWLCImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSWLCImg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblSWLCImgMouseDragged(evt);
            }
        });

        lblBolImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logos/bolton_logo.png"))); // NOI18N
        lblBolImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBolImg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblBolImgMouseDragged(evt);
            }
        });

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnLogout.setToolTipText("Close");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });

        btnMinimize.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        btnMinimize.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minimise.png"))); // NOI18N
        btnMinimize.setToolTipText("Minimize");
        btnMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
        });

        lblCopyright.setFont(new java.awt.Font("URW Gothic L", 0, 11)); // NOI18N
        lblCopyright.setText("Developed by Tharindu Athukorala");

        lblDevelopedTxt.setFont(new java.awt.Font("URW Gothic L", 0, 12)); // NOI18N
        lblDevelopedTxt.setText("Notifier © 2022");

        lblUserName.setFont(new java.awt.Font("URW Gothic L", 1, 14)); // NOI18N
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUserName.setText("Tharindu Dananjaya");

        txtDate.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(153, 153, 153));
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBackToHome.setDisplayedMnemonic('W');
        btnBackToHome.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        btnBackToHome.setText("Back to Home");
        btnBackToHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackToHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackToHomeMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        lblTitle.setFont(new java.awt.Font("URW Gothic L", 0, 12)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(153, 153, 153));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("What's on your mind?");
        jPanel1.add(lblTitle);
        lblTitle.setBounds(130, 20, 220, 40);

        txtPost.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        spPostWrapper.setViewportView(txtPost);

        jPanel1.add(spPostWrapper);
        spPostWrapper.setBounds(20, 60, 420, 140);

        btnPost.setBackground(new java.awt.Color(0, 153, 255));
        btnPost.setFont(new java.awt.Font("URW Gothic L", 1, 14)); // NOI18N
        btnPost.setForeground(new java.awt.Color(255, 255, 255));
        btnPost.setText("Post");
        btnPost.setBorder(null);
        btnPost.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPostMouseClicked(evt);
            }
        });
        btnPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostActionPerformed(evt);
            }
        });
        jPanel1.add(btnPost);
        btnPost.setBounds(20, 220, 193, 40);

        lblBackgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/post_background.png"))); // NOI18N
        jPanel1.add(lblBackgroundImg);
        lblBackgroundImg.setBounds(250, 20, 490, 380);

        lblTitle1.setFont(new java.awt.Font("URW Gothic L", 1, 14)); // NOI18N
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle1.setText("Add your Post");
        jPanel1.add(lblTitle1);
        lblTitle1.setBounds(20, 20, 100, 40);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblSWLCImg, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblBolImg)
                .addGap(12, 12, 12)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202)
                        .addComponent(btnMinimize)
                        .addGap(6, 6, 6)
                        .addComponent(btnLogout))
                    .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(separatorHrz, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(lblDevelopedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(345, 345, 345)
                    .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnBackToHome, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSWLCImg)
                    .addComponent(lblBolImg)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnLogout))
                        .addGap(8, 8, 8)
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(separatorHrz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBackToHome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblDevelopedTxt))
                    .addComponent(lblCopyright)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblSWLCImgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSWLCImgMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_lblSWLCImgMouseDragged

    private void lblBolImgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBolImgMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_lblBolImgMouseDragged

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        int isClose = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
        if (isClose == 0) {
            this.dispose();
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(Home.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void mainPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseMoved
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_mainPanelMouseMoved

    private void mainPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_mainPanelMouseDragged

    private void btnPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPostActionPerformed

    private void btnPostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPostMouseClicked

    }//GEN-LAST:event_btnPostMouseClicked

    private void btnBackToHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackToHomeMouseClicked
        backToHome();
    }//GEN-LAST:event_btnBackToHomeMouseClicked

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
            java.util.logging.Logger.getLogger(PostContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PostContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PostContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PostContent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PostContent().setVisible(true);
            }
        });
    }

    private void backToHome() {
        PostContent.this.dispose();
        new Home(loggedUserObj).setVisible(rootPaneCheckingEnabled);
    }

    private void setSessionDetails() {
        lblUserName.setText(loggedUserObj.getName());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBackToHome;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnMinimize;
    private javax.swing.JButton btnPost;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBackgroundImg;
    private javax.swing.JLabel lblBolImg;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDevelopedTxt;
    private javax.swing.JLabel lblSWLCImg;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSeparator separatorHrz;
    private javax.swing.JScrollPane spPostWrapper;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextPane txtPost;
    // End of variables declaration//GEN-END:variables
}
