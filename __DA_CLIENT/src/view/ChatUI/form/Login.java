package view.ChatUI.form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import view.ChatUI.event.EventLogin;
import view.ChatUI.event.EventMessage;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.PanelSlide;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import io.socket.client.Ack;
import model.Chat.Model_Message;
import model.Chat.Model_Register;
import model.Chat.Model_User_Account;
import service.Service;

public class Login extends JPanel {

    private PanelSlide slide;
	private P_Login login;
	private P_Register register;

    public Login(JFrame frameParent) {
    	
    	slide = new PanelSlide();
    	
        slide.setBackground(new java.awt.Color(255, 255, 255));
//        slide.setBackground(new java.awt.Color(200, 200, 200));
    	
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
    				.addContainerGap(840, Short.MAX_VALUE)
    				.addComponent(slide, 354, 354, 354)
    				.addGap(116))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(174)
    				.addComponent(slide, 435, 435, 435)
    				.addGap(192))
    	);
    	setLayout(groupLayout);

        PublicEvent.getInstance().addEventLogin(new EventLogin() {
            @Override
            public void login() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PublicEvent.getInstance().getEventMain().initChat();
                        Service.getInstance().listProject();
                        setVisible(false);
                        frameParent.dispose();
                    }
                }).start();
            }
            @Override
            public void register(Model_Register data, EventMessage message) {

            }
            @Override
            public void goRegister() {
                slide.show(1);
            }

            @Override
            public void goLogin() {
                slide.show(0);
            }
        });
      
        login = new P_Login();
        register = new P_Register();
//        slide.setLayout(new CardLayout());
        slide.setLayout(new FlowLayout());
        slide.init(login, register);

    }

	public P_Login getLogin() {
		return login;
	}

	public P_Register getRegister() {
		return register;
	}
}
