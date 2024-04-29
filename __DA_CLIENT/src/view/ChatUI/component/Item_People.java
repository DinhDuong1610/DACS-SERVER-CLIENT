package view.ChatUI.component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.Chat.Model_User_Account;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.ImageAvatar;

public class Item_People extends JPanel {
	private ImageAvatar imageAvatar1;
    private JLabel lb;
    private boolean mouseOver;
    private final Model_User_Account user;
	
    public Item_People(Model_User_Account user) {
    	this.user = user;
        initComponents();
        lb.setText(user.getUserName());
        init();
    }
    
    public void init() {
    	addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(255, 255, 255));
                mouseOver = false;
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver) {
                    PublicEvent.getInstance().getEventMain().selectUser(user);
                }
            }
        });
    }

    private void initComponents() {

        imageAvatar1 = new ImageAvatar();
        lb = new JLabel();

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));

        lb.setFont(new java.awt.Font("sansserif", Font.BOLD, 18)); // NOI18N
        lb.setText("Name");
        setBackground(new Color(255, 255, 255));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE,45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    public Model_User_Account getUser() {
        return user;
    }
}
