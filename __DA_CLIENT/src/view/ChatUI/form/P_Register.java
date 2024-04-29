package view.ChatUI.form;

import javax.swing.*;

import view.Panel_Register;
import view.ChatUI.event.EventMessage;
import view.ChatUI.event.PublicEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Chat.Model_Message;
import model.Chat.Model_Register;
import service.Service;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

public class P_Register extends javax.swing.JPanel {
    private JButton cmdBackLogin;
    private JButton cmdRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel lbTitle;
    private JPasswordField txtPass;
    private JPasswordField txtRePassword;
    private JTextField txtUser;
    private JLabel lbError;
	
    public P_Register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	setSize(354, 435);
    	
        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtUser = new javax.swing.JTextField();
        txtUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtPass = new javax.swing.JPasswordField();
        txtPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cmdRegister = new javax.swing.JButton();
        cmdRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        cmdBackLogin = new javax.swing.JButton();
        txtRePassword = new javax.swing.JPasswordField();
        txtRePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 20));

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new Font("SansSerif", Font.PLAIN, 40)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Register");

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

        cmdRegister.setText("Register");
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });

        cmdBackLogin.setFont(new Font("SansSerif", Font.PLAIN, 14)); // NOI18N
        cmdBackLogin.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackLogin.setText("Back Login");
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackLoginActionPerformed(evt);
            }
        });

        jLabel3.setText("Confirm Password");
        
        lbError = new JLabel("");
        lbError.setForeground(Color.RED);
        lbError.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(30, Short.MAX_VALUE))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        					.addContainerGap())))
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(txtRePassword, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(18)
        			.addComponent(cmdRegister, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lbError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        				.addComponent(cmdBackLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(lbTitle)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel3)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtRePassword, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(cmdRegister, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(cmdBackLogin)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lbError, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        			.addGap(45))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_cmdBackLoginActionPerformed

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        String userName = txtUser.getText().trim();
        String password = String.valueOf(txtPass.getPassword());
        String confirmPassword = String.valueOf(txtRePassword.getPassword());
        if (userName.equals("")) {
            txtUser.grabFocus();
        } else if (password.equals("")) {
            txtPass.grabFocus();
        } else if (!password.equals(confirmPassword)) {
        	txtRePassword.grabFocus();
        } else {
            Model_Register data = new Model_Register(userName, password);
            Service.getInstance().sendRegister(data.toJsonObject());
        }
    }
    
    public void checkRegister() {
        Model_Message model_Message = Service.getInstance().getModel_message();
        if (!model_Message.isAction()) {
            lbError.setText(model_Message.getMessage());
            lbError.setForeground(Color.red);
        } else {
//            PublicEvent.getInstance().getEventLogin().goLogin();
        	lbError.setText(model_Message.getMessage());
        	lbError.setForeground(Color.green);
            
            
//        	Panel_Register register = new Panel_Register();
//        	JDialog dialog = new JDialog();
//    		dialog.setLayout(new GridLayout(1,1));
//    		dialog.setSize(1400, 620);
//    		dialog.setLocationRelativeTo(null);
//        	dialog.add(register);
//        	dialog.setVisible(true);
        }
    }


}
