package view.CommunityUI.form;

import javax.swing.JPanel;

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
	
	public Screen(int projectId) {
		this.projectId = projectId;
		
		setBackground(new Color(169, 169, 169));
		
		panel = new JPanel();
		
		JButton bt_leave = new JButton("LEAVE");
		
		JButton bt_share = new JButton("SHARE");
		bt_share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("share màn hình");
		        new Thread(() -> {
					while(true) {
						try {
							Robot rob = new Robot();  
							Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                            BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
//			                Service.getInstance().share(img);
//                            Service.getInstance().share(img);
			                
			                try {
								Thread.sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
	                    try {
	                        Thread.sleep(500);
	                    } catch (Exception e1) {
	                    	e1.printStackTrace();
	                    }
					}

		        }).start();
			}
		});
		
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

	public JPanel getPanel() {
		return panel;
	}
	
}
