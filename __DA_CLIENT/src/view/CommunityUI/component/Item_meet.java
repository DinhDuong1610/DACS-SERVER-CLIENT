package view.CommunityUI.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import view.ChatUI.swing.ImageAvatar;
import view.ChatUI.swing.JIMSendTextPane;
import javax.swing.JButton;

public class Item_meet extends JPanel{
	private ImageAvatar imageAvatar;

	public Item_meet(String userName, String time, String nameMeet, String date) {
		setBorder(new EmptyBorder(0, 20, 10, 10));
		
		JPanel panel_title = new JPanel();
		
		JPanel panel_content = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_content, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_content, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lb_nameMeet = new JLabel();
		lb_nameMeet.setForeground(new Color(123, 104, 238));
		lb_nameMeet.setText(nameMeet);
		lb_nameMeet.setPreferredSize(new Dimension(lb_nameMeet.getPreferredSize().width, lb_nameMeet.getPreferredSize().height));
		lb_nameMeet.setFont(new Font("Tahoma", Font.BOLD, 26));
		JLabel lb_date = new JLabel();
		lb_date.setText(date);
		lb_date.setPreferredSize(new Dimension(lb_date.getPreferredSize().width, lb_date.getPreferredSize().height));
		lb_date.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton bt_join = new JButton("Join");
		bt_join.setForeground(new Color(123, 104, 238));
		bt_join.setFont(new Font("Tahoma", Font.BOLD, 22));
		GroupLayout gl_panel_content = new GroupLayout(panel_content);
		gl_panel_content.setHorizontalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_content.createSequentialGroup()
					.addGroup(gl_panel_content.createParallelGroup(Alignment.LEADING)
						.addComponent(lb_date, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
						.addComponent(lb_nameMeet, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(bt_join, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
		);
		gl_panel_content.setVerticalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_content.createSequentialGroup()
					.addGroup(gl_panel_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_content.createSequentialGroup()
							.addGap(10)
							.addComponent(bt_join, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_content.createSequentialGroup()
							.addComponent(lb_nameMeet, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lb_date, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
					.addGap(8))
		);
		panel_content.setLayout(gl_panel_content);
		
//		JLabel lb_avatar = new JLabel("");
		imageAvatar = new ImageAvatar();
		imageAvatar.setBorderSize(0);
		imageAvatar.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
		
		JLabel lb_userName = new JLabel(userName);
		lb_userName.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lb_time = new JLabel(time);
		lb_time.setForeground(Color.GRAY);
		lb_time.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel_title = new GroupLayout(panel_title);
		gl_panel_title.setHorizontalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_title.createSequentialGroup()
					.addComponent(imageAvatar, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_title.createParallelGroup(Alignment.LEADING)
						.addComponent(lb_userName, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
						.addComponent(lb_time, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(639, Short.MAX_VALUE))
		);
		gl_panel_title.setVerticalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_title.createSequentialGroup()
					.addContainerGap()
					.addComponent(lb_userName)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lb_time)
					.addContainerGap())
				.addComponent(imageAvatar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panel_title.setLayout(gl_panel_title);
		setLayout(groupLayout);
		
		setBackground(Color.white);
		panel_content.setBackground(Color.white);
		panel_title.setBackground(Color.white);
	}
}
