package view.ChatUI.form;

import java.awt.Color;

import javax.swing.*;

import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;

public class Home extends JPanel{
	
	private Chat chat;

	public Home() {
		setSize(1472, 803);
		setLayout(new MigLayout("fillx, filly", "0[300!]5[fill, 100%]0", "0[fill]0"));
		
        this.add(new Menu_Left());
        chat = new Chat();
        this.add(chat);
        chat.setVisible(false);
	}
	
    public void setUser(Model_User_Account user) {
        chat.setUser(user);
        chat.setVisible(true);
    }

    public void updateUser(Model_User_Account user) {
        chat.updateUser(user);
    }
}
