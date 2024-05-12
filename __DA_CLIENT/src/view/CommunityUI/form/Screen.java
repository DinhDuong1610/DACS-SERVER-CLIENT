package view.CommunityUI.form;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;

public class Screen extends JPanel{
	public Screen() {
		setBackground(new Color(169, 169, 169));
		
		JPanel panel = new JPanel();
		
		JButton bt_leave = new JButton("LEAVE");
		
		JButton bt_share = new JButton("SHARE");
		
		JPanel panel_button_voice = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(250)
					.addComponent(bt_share, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addGap(204)
					.addComponent(bt_leave, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
					.addGap(165)
					.addComponent(panel_button_voice, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addGap(304))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_button_voice, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(bt_share, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(bt_leave, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addGap(23))
		);
		panel_button_voice.setLayout(new CardLayout(0, 0));
		
		JButton bt_on = new JButton("ON");
		panel_button_voice.add(bt_on, "ON");
		setLayout(groupLayout);
		
		JButton bt_off= new JButton("OFF");
		panel_button_voice.add(bt_off, "OFF");
	}
}
