package controller.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CommunityUI.form.Body;

public class CardLayout_Community implements ActionListener{
	private Body body;
	
	public CardLayout_Community(Body body) {
		super();
		this.body = body;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == body.getTitle().getBt_post()) {
			body.getPage().getCardLayout_Page().show(body.getPage(), "panel_post");
		}
		else if(e.getSource() == body.getTitle().getBt_event()) {
			body.getPage().getCardLayout_Page().show(body.getPage(), "panel_event");
		}
		
	}

}
