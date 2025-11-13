package view;

import controller.ChatController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import model.Message;
import model.User;
import util.RoundedJButton;
import util.RoundedJPanel;
import util.RoundedJTextFiled;

public class ChatWindow extends JFrame {

    private final ChatController chatController;
    private User user = null;
    private String userName = "";

    public ChatWindow(ChatController chatController, User user) {
        super(user.getName());
        initComponents();
        this.chatController = chatController;
        this.user = user;
        this.userName = user.getName();
        lblUser.setText(userName);
        setLocationRelativeTo(null);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        messagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        RoundedJButton.makeButtonRounded(btnSend, 30, new Color(7, 94, 84), Color.WHITE);
        RoundedJTextFiled.makeTextFieldRounded(txtMessage, 15, Color.WHITE, Color.GRAY);
        setVisible(true);

        //Unregister window when window closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                chatController.unRegisterWindow(ChatWindow.this);
            }
        });
    }

    public void displayMessage(String sender, String message) {
        boolean isOwnMessage = user != null && sender.equals(user.getName());
        String displayText = isOwnMessage ? "Me : " + message : sender + " : " + message;
        makeMessageBubble(displayText, isOwnMessage, messagePanel, scrollPane);
    }

    public static void makeMessageBubble(String text, boolean isOwnMessage, JPanel messagePanel, JScrollPane scrollPane) {
        // One fixed-height row per message
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS)); // horizontal layout (no stretch)
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // prevents vertical stretching

        // Bubble wrapper
        JPanel bubbleWrapper = new JPanel(new FlowLayout(isOwnMessage ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 0));
        bubbleWrapper.setOpaque(false);
        bubbleWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        bubbleWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        // Bubble component (rounded panel)
        RoundedJPanel bubble = new RoundedJPanel(text,
                isOwnMessage ? new Color(7, 94, 84) : new Color(220, 248, 198),
                isOwnMessage ? Color.WHITE : Color.BLACK);

        // Add bubble to wrapper and wrapper to row
        bubbleWrapper.add(bubble);
        row.add(bubbleWrapper);

        // Add to message panel
        messagePanel.add(row);
        messagePanel.add(Box.createRigidArea(new Dimension(0, 8))); // fixed 8px gap

        // Force top alignment, prevent auto-stretch
        messagePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        messagePanel.revalidate();
        messagePanel.repaint();

        // Auto scroll to bottom
        SwingUtilities.invokeLater(() -> {
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
        });
    }

    public User getUser() {
        return user;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAddSender = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        messagePanel = new javax.swing.JPanel();
        txtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(7, 94, 84));

        btnAddSender.setBackground(new java.awt.Color(7, 94, 84));
        btnAddSender.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnAddSender.setForeground(new java.awt.Color(255, 255, 255));
        btnAddSender.setText("+");
        btnAddSender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSenderActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        lblUser.setBackground(new java.awt.Color(7, 64, 84));
        lblUser.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddSender, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblUser))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddSender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
        messagePanel.setLayout(messagePanelLayout);
        messagePanelLayout.setHorizontalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );
        messagePanelLayout.setVerticalGroup(
            messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(messagePanel);

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtMessage))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSenderActionPerformed
        new AddSenderWindow(chatController).setVisible(true);
    }//GEN-LAST:event_btnAddSenderActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        final String message = txtMessage.getText();

        if (!(message.isEmpty()) && user != null) {
            chatController.broadcastMessage(new Message(user, message));
            txtMessage.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please Enter the Message First");
        }

    }//GEN-LAST:event_btnSendActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSender;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel messagePanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
