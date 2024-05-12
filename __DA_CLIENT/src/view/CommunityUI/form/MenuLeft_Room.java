package view.CommunityUI.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.Chat.Model_User_Account;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import view.MainUI;
import view.ChatUI.component.Item_People;
import view.ChatUI.event.EventMenuLeft;
import view.ChatUI.event.PublicEvent;
import javax.swing.SwingConstants;

public class MenuLeft_Room extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User_Account> userAccount;
	private int projectId;

	public MenuLeft_Room(int projectId) {
		this.projectId = projectId;
		
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[35]0[100%,fill]0"));
		
		
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
		JLabel label = new JLabel("NGƯỜI THAM GIA");
		label.setOpaque(true);
		label.setBackground(new Color(0, 191, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(label, "width 300:300:300, height 35:35:35, wrap");
		
		userAccount = new ArrayList<>();
//        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
//            @Override
//            public void newUser(Model_User_Account d) {	
//                    userAccount.add(d);
//                    panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
//                    panel_menu_list.repaint();
//                    panel_menu_list.revalidate();
//            }
//        });
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		
//		showPeople();
		setBackground(Color.blue);
		panel_menu_list.removeAll();
	}
	
	public void showPeople() {
		panel_menu_list.removeAll();
		
        for (Model_User_Account d : userAccount) {
        	panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
        }
        
//		panel_menu_list.add(new Item_People("Đính Dương"), "width 296:296:296, height 50:50:50, wrap");
//		panel_menu_list.add(new Item_People("Lê Hữu Anh Tú"), "width 296:296:296, height 50:50:50, wrap");
//		panel_menu_list.add(new Item_People("Cao Hoàng Phước Bảo"), "width 296:296:296, height 50:50:50, wrap");
//		for(int i = 1; i <= 20; i++) {
//			panel_menu_list.add(new Item_People("People " + i), "width 296:296:296, height 50:50:50, wrap");
//		}
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}
	
	public void showGroup() {
//		panel_menu_list.removeAll();
//		for(int i = 1; i <= 10; i++) {
//			panel_menu_list.add(new Item_People("Group " + i), "width 296:296:296, height 50:50:50, wrap");
//		}
//		panel_menu_list.repaint();
//		panel_menu_list.revalidate();
	}
	
	public boolean searchUser(String userName) {
		for(Model_User_Account user : userAccount) {
			if(user.getUserName().equalsIgnoreCase(userName)) {
				return true;
			}
		}
		return false;
	}

	public JLayeredPane getPanel_menu_list() {
		return panel_menu_list;
	}

	public void setPanel_menu_list(JLayeredPane panel_menu_list) {
		this.panel_menu_list = panel_menu_list;
	}
	
	
}
