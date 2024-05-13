package view.ChatUI.form;

import java.awt.Color;

import javax.swing.*;

import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;

public class Home extends JPanel{
	
	private Chat chat;
	private Menu_Left menu_Left;

	public Home() {
		setSize(1472, 803);
		setLayout(new MigLayout("fillx, filly", "0[300!]5[fill, 100%]0", "0[fill]0"));
		menu_Left = new Menu_Left();
        this.add(menu_Left);
        chat = new Chat();
        this.add(chat);
        chat.setVisible(false);
	}
	
	public void loadActive(int userId, boolean isActive) {
		menu_Left.loadActive(userId, isActive);
		if(chat.getChatTitle().getUser().getUser_Id() == userId) {
			chat.getChatTitle().setStatusText(isActive);
		}
	}
	
    public void setUser(Model_User_Account user) {
        chat.setUser(user);
        chat.setVisible(true);
    }

    public void updateUser(Model_User_Account user) {
        chat.updateUser(user);
    }

	public Chat getChat() {
		return chat;
	}

	public Menu_Left getMenu_Left() {
		return menu_Left;
	}
	
	
    
    
}
