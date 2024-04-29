package view.ChatUI.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import view.ChatUI.swing.ImageAvatar;

public class Chat_Left_With_Profile extends JLayeredPane{
	private ImageAvatar IaImage;
	private JLayeredPane jLayeredPane;
	private Chat_Item txt;
	
	public Chat_Left_With_Profile() {
		jLayeredPane = new JLayeredPane();
		IaImage = new ImageAvatar();
		txt = new Chat_Item();
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		IaImage.setBorderSize(0);
		IaImage.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
        IaImage.setMaximumSize(new Dimension(31, 31));
        IaImage.setMinimumSize(new Dimension(31, 31));
        IaImage.setPreferredSize(new Dimension(31, 31));
        
        jLayeredPane.setLayer(IaImage, JLayeredPane.DEFAULT_LAYER);

        GroupLayout jLayeredPane1Layout = new GroupLayout(jLayeredPane);
        jLayeredPane.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(IaImage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(IaImage, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
        );
        
        add(jLayeredPane);
        add(txt);
        setBackground(new Color(242, 242, 242));
	}
	
	public void setUserProfile(String user) {
		txt.setUserProfile(user);
	}
	
	public void setImageProfile(Icon image) {
		IaImage.setImage(image);
	}
	
	public void setText(String text) {
		if(text.equals("")) {
			txt.hideText();
		}
		else {
			txt.setText(text);
		}
	}
	
    public void setImage(Icon... image) {
        txt.setImage(false, image);
    }
    
    
    public void setTime() {
        txt.setTime("10:30 PM"); 
    }
    
    public void setFile(String fileName, String fileSize) {
        txt.setFile(fileName, fileSize);
    }
}
