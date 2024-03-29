package com.swlc.bolton.notifier.views;

import com.swlc.bolton.notifier.controller.ControllerFactory;
import com.swlc.bolton.notifier.controller.SubscriptionController;
import com.swlc.bolton.notifier.controller.UserController;
import com.swlc.bolton.notifier.data.store.ChannelObserver;
import com.swlc.bolton.notifier.data.store.impl.ChannelProvider;
import com.swlc.bolton.notifier.dto.PostDTO;
import com.swlc.bolton.notifier.dto.SubscribeUserDTO;
import com.swlc.bolton.notifier.dto.SubscriptionDTO;
import com.swlc.bolton.notifier.dto.UserDTO;
import com.swlc.bolton.notifier.enums.ControllerTypes;
import com.swlc.bolton.notifier.enums.ObserverType;
import com.swlc.bolton.notifier.json.CommonResponse;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.Timer;

/**
 *
 * @author athukorala
 */
public class Home extends javax.swing.JFrame implements ChannelObserver {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    // for draggable
    private int xMouse = 0;
    private int yMouse = 0;

    private UserDTO loggedUserObj;
    private SubscriptionController subscriptionController;
    private UserController userController;
    private ChannelProvider channelProvider;

    //for dynamic list 
    JPanel containerPosts = new JPanel(new GridLayout(0, 1));

    public Home(UserDTO userDTO, ChannelProvider channelProvider) {
        this.loggedUserObj = userDTO;
        this.channelProvider = channelProvider;

        initComponents();
        setSize(750, 535);
        showTime();
        this.setLocation(dim.width, 0);

        // initalizing
        subscriptionController = (SubscriptionController) ControllerFactory.getInstance().getController(ControllerTypes.SUBSCRIPTION);
        userController = (UserController) ControllerFactory.getInstance().getController(ControllerTypes.USER);
        
        // session details
        setSessionDetails();

        // retrieve friends (with subscription status)
        retrieveAllSubscriptionHandler();

        // show subscriber count
        displaySubscriberCountHandler();

        // underline deactivate button text
        btnDeactivate.setText("<html><u>Remove Account</u></html>");
    }
    public Home() {
        initComponents();
        setSize(750, 535);
        showTime();
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

    // for the testing purpose
    private void loadPostedContents() {
        JPanel container = new JPanel(new GridLayout(0, 1)); // 1 column variable
        for (int i = 0; i < 8; i++) {
            JPanel subPanel = new JPanel();
            subPanel.setBackground(new java.awt.Color(255, 255, 255));

            JSeparator sep = new JSeparator();

            JTextPane txtPane = new JTextPane();
            txtPane.setEditable(false);
            txtPane.setBackground(new java.awt.Color(255, 255, 255));
            txtPane.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N

            JLabel lblPublished = new JLabel();
            lblPublished.setBackground(new java.awt.Color(255, 255, 255));
            lblPublished.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
            lblPublished.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

            txtPane.setText("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
            lblPublished.setText("- Posted by Tharindu Athukorala on June 31, 2022 03:34 PM");

            // ----
            javax.swing.GroupLayout subPanelLayout = new javax.swing.GroupLayout(subPanel);
            subPanel.setLayout(subPanelLayout);
            subPanelLayout.setHorizontalGroup(
                    subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(subPanelLayout.createSequentialGroup()
                                    .addGroup(subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(subPanelLayout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addComponent(txtPane, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                                            .addGroup(subPanelLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(lblPublished, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addContainerGap())
                            .addComponent(sep)
            );
            subPanelLayout.setVerticalGroup(
                    subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(txtPane, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblPublished, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            //        ---------
            container.add(subPanel);
        }
        spPostedContent.setViewportView(container);
    }

    private void setSessionDetails() {
        lblUserName.setText(loggedUserObj.getName());
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
        lblPost = new javax.swing.JLabel();
        lblFriends = new javax.swing.JLabel();
        btnPost = new javax.swing.JButton();
        btnDeactivate = new javax.swing.JLabel();
        postPanel = new javax.swing.JPanel();
        spPostedContent = new javax.swing.JScrollPane();
        friendPanel = new javax.swing.JPanel();
        spFriends = new javax.swing.JScrollPane();
        lblSubCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(700, 400));

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
        lblUserName.setText("Username");

        txtDate.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(153, 153, 153));
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtDate.setText("Date & Time");
        txtDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblPost.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        lblPost.setText("Live Posted Contents");

        lblFriends.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N
        lblFriends.setText("Active Contacts");

        btnPost.setBackground(new java.awt.Color(0, 153, 255));
        btnPost.setFont(new java.awt.Font("URW Gothic L", 1, 14)); // NOI18N
        btnPost.setForeground(new java.awt.Color(255, 255, 255));
        btnPost.setText("Content Posting");
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

        btnDeactivate.setFont(new java.awt.Font("URW Gothic L", 0, 12)); // NOI18N
        btnDeactivate.setForeground(new java.awt.Color(255, 0, 0));
        btnDeactivate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDeactivate.setText("Remove Account");
        btnDeactivate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeactivate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeactivateMouseClicked(evt);
            }
        });

        postPanel.setBackground(new java.awt.Color(255, 255, 255));

        spPostedContent.setBackground(new java.awt.Color(255, 255, 255));
        spPostedContent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout postPanelLayout = new javax.swing.GroupLayout(postPanel);
        postPanel.setLayout(postPanelLayout);
        postPanelLayout.setHorizontalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postPanelLayout.createSequentialGroup()
                .addComponent(spPostedContent, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        postPanelLayout.setVerticalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spPostedContent, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        friendPanel.setBackground(new java.awt.Color(255, 255, 255));

        spFriends.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout friendPanelLayout = new javax.swing.GroupLayout(friendPanel);
        friendPanel.setLayout(friendPanelLayout);
        friendPanelLayout.setHorizontalGroup(
            friendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spFriends, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        friendPanelLayout.setVerticalGroup(
            friendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblSubCount.setFont(new java.awt.Font("URW Gothic L", 1, 13)); // NOI18N
        lblSubCount.setForeground(new java.awt.Color(0, 153, 51));
        lblSubCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separatorHrz)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(lblSWLCImg, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBolImg)
                        .addGap(12, 12, 12)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeactivate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMinimize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLogout))
                            .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblPost, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(postPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(lblDevelopedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(friendPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCopyright)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(lblFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblSubCount, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSWLCImg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBolImg, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLogout)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnDeactivate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                            .addComponent(btnMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(separatorHrz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFriends, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblSubCount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(postPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblDevelopedTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        int isClose = JOptionPane.showConfirmDialog(this, "Are you sure you want to Logout?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
        if (isClose == 0) {
            this.dispose();
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
//        System.exit(0);
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void mainPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_mainPanelMouseDragged

    private void mainPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseMoved
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_mainPanelMouseMoved

    private void lblSWLCImgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSWLCImgMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_lblSWLCImgMouseDragged

    private void lblBolImgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBolImgMouseDragged
        draggableWindow(evt);
    }//GEN-LAST:event_lblBolImgMouseDragged

    private void btnPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPostActionPerformed

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(Home.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnPostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPostMouseClicked
//        Home.this.dispose();
        new PostContent(loggedUserObj, channelProvider).setVisible(true);
    }//GEN-LAST:event_btnPostMouseClicked

    private void btnDeactivateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeactivateMouseClicked
        int isSure = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove your account?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
        if (isSure == 0) {
            CommonResponse removeResp = userController.removeAccountHandler(loggedUserObj);
            if (removeResp.isSuccess()) {
                this.dispose();
                channelProvider.sendNotification(loggedUserObj, ObserverType.REMOVE_ACCOUNT);
            } else {
                JOptionPane.showMessageDialog(this, removeResp.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeactivateMouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    public void displaySubscriberCountHandler() {
        CommonResponse resp = subscriptionController.getSubscribedCountHandler(loggedUserObj.getId());
        if (resp.isSuccess()) {
            long subsCount = (long) resp.getBody();
            lblSubCount.setText(subsCount > 0 ? subsCount + " subscriber(s)" : "");
        }
    }

    public void retrieveAllSubscriptionHandler() {
        CommonResponse resp = subscriptionController.retrieveAllSubscriptionHandler(loggedUserObj);
        if (resp.isSuccess()) {
            ArrayList<SubscribeUserDTO> subscribeUsers = (ArrayList<SubscribeUserDTO>) resp.getBody();
            JPanel containerSubscription = new JPanel(new GridLayout(0, 1)); // 1 column variable
            subscribeUsers.forEach((SubscribeUserDTO obj) -> {
                JPanel userWrapperl = new JPanel();
                userWrapperl.setBackground(new java.awt.Color(255, 255, 255));

                JSeparator sep2 = new JSeparator();

                JLabel lblRegUser = new JLabel();
                lblRegUser.setBackground(new java.awt.Color(255, 255, 255));
                lblRegUser.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
                lblRegUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                lblRegUser.setToolTipText(obj.getUserDTO().getName());
                lblRegUser.setText(obj.getUserDTO().getName());

                JButton btnSubscribe = new JButton();
                btnSubscribe.setBackground(obj.isIsSubscribe() ? new java.awt.Color(102, 102, 102) : new java.awt.Color(255, 0, 0));
                btnSubscribe.setFont(new java.awt.Font("URW Gothic L", 1, 13)); // NOI18N
                btnSubscribe.setForeground(new java.awt.Color(255, 255, 255));
                btnSubscribe.setText(obj.isIsSubscribe() ? "Unsubscribe" : "Subscribe");
                btnSubscribe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                btnSubscribe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                btnSubscribe.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        CommonResponse response = subscriptionController.subscriptionUserHandler(new SubscriptionDTO(obj.getUserDTO().getId(), loggedUserObj.getId()));
                        if (response.isSuccess()) {
                            channelProvider.sendNotification(obj.getUserDTO(), ObserverType.SUBSCRIBED_COUNT);
                            retrieveAllSubscriptionHandler();
                        }
                    }
                });

                // ---- SCROLLABLE CONTENT - CODE --------
                javax.swing.GroupLayout userWrapperlLayout = new javax.swing.GroupLayout(userWrapperl);
                userWrapperl.setLayout(userWrapperlLayout);
                userWrapperlLayout.setHorizontalGroup(
                        userWrapperlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sep2)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userWrapperlLayout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                .addGroup(userWrapperlLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblRegUser, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                userWrapperlLayout.setVerticalGroup(
                        userWrapperlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userWrapperlLayout.createSequentialGroup()
                                        .addContainerGap(15, Short.MAX_VALUE)
                                        .addComponent(lblRegUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(btnSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sep2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                // ---- END CODE --------
                containerSubscription.add(userWrapperl);
            });
            spFriends.setViewportView(containerSubscription);

        }
    }

    public void sampleDataHandler() {

    }

    // testing
    private void getAllActiveUsers() {

        ArrayList<SubscribeUserDTO> activeUsers = new ArrayList();
        SubscribeUserDTO subscribeUserDTO = new SubscribeUserDTO();
        subscribeUserDTO.setName("Kisandu Vidujaya Athukorala");
        subscribeUserDTO.setIsSubscribe(false);
        activeUsers.add(subscribeUserDTO);
//        
//        activeUsers.add(new SubscribeUserDTO("Akram Sheshad", false));
//        activeUsers.add(new SubscribeUserDTO("Mohan Jayalal", true));
//        activeUsers.add(new SubscribeUserDTO("Kamal Perea", false));

        JPanel containerSubscription = new JPanel(new GridLayout(0, 1)); // 1 column variable

        for (int i = 0; i < activeUsers.size(); i++) {
            SubscribeUserDTO activeUser = activeUsers.get(i);
            JPanel userWrapperl = new JPanel();
            userWrapperl.setBackground(new java.awt.Color(255, 255, 255));

            JSeparator sep2 = new JSeparator();

            JLabel lblRegUser = new JLabel();
            lblRegUser.setBackground(new java.awt.Color(255, 255, 255));
            lblRegUser.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
            lblRegUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblRegUser.setToolTipText(activeUser.getName());
            lblRegUser.setText(activeUser.getName());

            JButton btnSubscribe = new JButton();
            btnSubscribe.setBackground(activeUser.isIsSubscribe() ? new java.awt.Color(102, 102, 102) : new java.awt.Color(255, 0, 0));
            btnSubscribe.setFont(new java.awt.Font("URW Gothic L", 1, 13)); // NOI18N
            btnSubscribe.setForeground(new java.awt.Color(255, 255, 255));
            btnSubscribe.setText(activeUser.isIsSubscribe() ? "Unsubscribe" : "Subscribe");
            btnSubscribe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            btnSubscribe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            // ----
            javax.swing.GroupLayout userWrapperlLayout = new javax.swing.GroupLayout(userWrapperl);
            userWrapperl.setLayout(userWrapperlLayout);
            userWrapperlLayout.setHorizontalGroup(
                    userWrapperlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sep2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userWrapperlLayout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            .addGroup(userWrapperlLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblRegUser, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            userWrapperlLayout.setVerticalGroup(
                    userWrapperlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userWrapperlLayout.createSequentialGroup()
                                    .addContainerGap(15, Short.MAX_VALUE)
                                    .addComponent(lblRegUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(btnSubscribe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(sep2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            //        ---------
            containerSubscription.add(userWrapperl);
        }
        spFriends.setViewportView(containerSubscription);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDeactivate;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JLabel btnMinimize;
    private javax.swing.JButton btnPost;
    private javax.swing.JPanel friendPanel;
    private javax.swing.JLabel lblBolImg;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDevelopedTxt;
    private javax.swing.JLabel lblFriends;
    private javax.swing.JLabel lblPost;
    private javax.swing.JLabel lblSWLCImg;
    private javax.swing.JLabel lblSubCount;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel postPanel;
    private javax.swing.JSeparator separatorHrz;
    private javax.swing.JScrollPane spFriends;
    private javax.swing.JScrollPane spPostedContent;
    private javax.swing.JLabel txtDate;
    // End of variables declaration//GEN-END:variables

    @Override
    public void notifyPost(PostDTO post) {
        String postedBy = loggedUserObj.getId() == post.getSharedUser().getId() ? "Me" : post.getSharedUser().getName();

        JPanel subPanel = new JPanel();
        subPanel.setBackground(new java.awt.Color(255, 255, 255));

        JSeparator sep = new JSeparator();

        JTextPane txtPane = new JTextPane();
        txtPane.setEditable(false);
        txtPane.setBackground(new java.awt.Color(255, 255, 255));
        txtPane.setFont(new java.awt.Font("URW Gothic L", 0, 14)); // NOI18N

        JLabel lblPublished = new JLabel();
        lblPublished.setBackground(new java.awt.Color(255, 255, 255));
        lblPublished.setFont(new java.awt.Font("URW Gothic L", 0, 13)); // NOI18N
        lblPublished.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txtPane.setText(post.getPost());
        lblPublished.setText("- Posted by " + postedBy + " on " + post.getTimestamp());

        // ----
        javax.swing.GroupLayout subPanelLayout = new javax.swing.GroupLayout(subPanel);
        subPanel.setLayout(subPanelLayout);
        subPanelLayout.setHorizontalGroup(
                subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(subPanelLayout.createSequentialGroup()
                                .addGroup(subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(subPanelLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(txtPane, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                                        .addGroup(subPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblPublished, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addComponent(sep)
        );
        subPanelLayout.setVerticalGroup(
                subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(txtPane, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPublished, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(sep, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        //        ---------
        containerPosts.add(subPanel);
        spPostedContent.setViewportView(containerPosts);
    }

    public UserDTO getLoggedUserObj() {
        return loggedUserObj;
    }

    @Override
    public void notifySubscribers(long subsCount) {
        lblSubCount.setText(subsCount > 0 ? subsCount + " subscriber(s)" : "");
    }

    @Override
    public void notifyAccountRemoved(UserDTO userDTO) {
        displaySubscriberCountHandler();
        retrieveAllSubscriptionHandler();
    }

    @Override
    public void notifyAccountCreated(UserDTO userDTO) {
        retrieveAllSubscriptionHandler();
    }
}
