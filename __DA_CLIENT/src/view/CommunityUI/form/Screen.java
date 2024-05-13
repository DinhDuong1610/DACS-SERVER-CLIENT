package view.CommunityUI.form;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.community.Model_Project;
import service.Service;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

public class Screen extends JPanel{
	private int projectId;
	private JPanel panel;
	private CardLayout cardLayout_share;
	private CardLayout cardLayout_mic;
	
	public Screen(int projectId) {
		this.projectId = projectId;
		
		setBackground(new Color(169, 169, 169));
		
		panel = new JPanel();
		
		JButton bt_leave = new JButton("LEAVE");
		
		JPanel panel_button_voice = new JPanel();
		
		JPanel panel_button_share = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(233)
					.addComponent(panel_button_share, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addGap(178)
					.addComponent(bt_leave, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(165)
					.addComponent(panel_button_voice, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(244))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_button_share, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(panel_button_voice, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(bt_leave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addGap(23))
		);
		
		cardLayout_share = new CardLayout(0, 0);
		panel_button_share.setLayout(cardLayout_share);
		
		JButton bt_on_share = new JButton("ON SHARE");
		bt_on_share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    cardLayout_share.next(panel_button_share);
                    Service.getInstance().setOn_img(true);            
                });			
			}
		});
		panel_button_share.add(bt_on_share, "bt_on_share");
		
		JButton bt_off_share = new JButton("OFF SHARE");
		panel_button_share.add(bt_off_share, "bt_off_share");
		bt_off_share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              SwingUtilities.invokeLater(() -> {
	                    cardLayout_share.next(panel_button_share);
	                    Service.getInstance().setOn_img(false);
	                });
			}
		});
		
		cardLayout_mic = new CardLayout(0, 0);
		panel_button_voice.setLayout(cardLayout_mic);
		
		JButton bt_on_mic = new JButton("ON MIC");
		bt_on_mic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout_mic.next(panel_button_voice);
				Service.getInstance().setOn_mic(true);
				Service.getInstance().setOn_loa(false);
			}
		});
		panel_button_voice.add(bt_on_mic, "bt_on_mic");
		setLayout(groupLayout);
		
		JButton bt_off_mic= new JButton("OFF MIC");
		bt_off_mic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout_mic.next(panel_button_voice);
				Service.getInstance().setOn_loa(true);
				Service.getInstance().setOn_mic(false);
			}
		});
		panel_button_voice.add(bt_off_mic, "bt_off_mic");
	}

	public JPanel getPanel() {
		return panel;
	}
}
