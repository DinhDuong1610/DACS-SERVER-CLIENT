package view.ChatUI.form;

import javax.swing.*;

import view.ChatUI.event.PublicEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Chat.Model_Login;
import model.Chat.Model_Message;
import model.Chat.Model_Register;
import service.Service;

import java.awt.Font;
import java.awt.Color;

public class P_Login extends JPanel{
    private JButton cmdLogin;
    private JButton cmdRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel lbTitle;
    private JPasswordField txtPass;
    private JTextField txtUser;
    private JLabel lbError;
    
    public P_Login() {
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
        cmdLogin = new javax.swing.JButton();
        cmdLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        cmdRegister = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new Font("SansSerif", Font.PLAIN, 40)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });

        cmdRegister.setFont(new Font("SansSerif", Font.PLAIN, 14)); // NOI18N
        cmdRegister.setForeground(new java.awt.Color(15, 128, 206));
        cmdRegister.setText("Register");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });
        
        lbError = new JLabel("");
        lbError.setForeground(Color.RED);
        lbError.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(20)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(cmdLogin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
        									.addGap(10))
        								.addComponent(txtUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
        							.addPreferredGap(ComponentPlacement.RELATED))))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(lbError, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
        						.addComponent(cmdRegister, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))))
        			.addGap(23))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(lbTitle)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel1)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        			.addGap(39)
        			.addComponent(cmdLogin, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(cmdRegister)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lbError, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addGap(27))
        );
        this.setLayout(layout);
    }
    
    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        PublicEvent.getInstance().getEventLogin().goRegister();
    }//GEN-LAST:event_cmdRegisterActionPerformed

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        String userName = txtUser.getText().trim();
        String password = String.valueOf(txtPass.getPassword());
        if (userName.equals("")) {
            txtUser.grabFocus();
        } else if (password.equals("")) {
            txtPass.grabFocus();
        } else {
            Model_Login data = new Model_Login(userName, password);
            Service.getInstance().sendLogin(data.toJsonObject());
        }
    }
    
    public void checkLogin() {
        Model_Message model_Message = Service.getInstance().getModel_message();
        if (!model_Message.isAction()) {
            lbError.setText(model_Message.getMessage());
        } else {
            PublicEvent.getInstance().getEventLogin().login();
        }
    }
    
}
