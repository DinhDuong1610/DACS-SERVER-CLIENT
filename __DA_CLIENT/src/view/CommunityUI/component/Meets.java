package view.CommunityUI.component;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class Meets extends JPanel{
	public Meets() {
		setLayout(new MigLayout("fillx", "100[fill]100", "0[]15"));	
		
		add(new Item_meet("Dương Đính", "10:44 PM", "Demo Project 1", "9:00 AM - 12:00 AM, 08/04/2024") , "wrap");
		add(new Item_meet("Dương Đính", "10:50 PM", "Demo Project 2", "12:00 AM - 3:00 PM, 10/04/2024") ,  "wrap");
		add(new Item_meet("Dương Đính", "10:44 PM", "Demo Project 3", "9:00 AM - 12:00 AM, 08/04/2024") ,  "wrap");
		add(new Item_meet("Dương Đính", "10:44 PM", "Demo Project 4", "9:00 AM - 12:00 AM, 08/04/2024") , "wrap");
		add(new Item_meet("Dương Đính", "10:50 PM", "Demo Project 5", "12:00 AM - 3:00 PM, 10/04/2024") ,  "wrap");
		add(new Item_meet("Dương Đính", "10:44 PM", "Demo Project 6", "9:00 AM - 12:00 AM, 08/04/2024") ,  "wrap");
	}
}
