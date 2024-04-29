package view.CommunityUI.form;

import javax.swing.*;

import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;

public class HomeCommu extends JPanel{
	private Menu_Left menuLeft;
	private Body body;
	
	private Model_Project project;
	
	public HomeCommu() {
		setLayout(new MigLayout("fillx, filly", "0[300!]5[fill, 100%]0", "0[fill]0"));
		menuLeft = new Menu_Left();
		body = new Body(null);
		this.add(menuLeft);
		this.add(body);
		body.setVisible(false);
	}
	
	public void selectedProject(Model_Project project) {
		this.project = project;
		body.setProject(project);
		body.getPage().getNews().removeAll();
		Service.getInstance().listPost(project.getProjectId());
		body.setVisible(true);
		body.getTitle().getLb_nameProject().setText(project.getProjectName());
	}
	
	public Menu_Left getMenuLeft() {
		return menuLeft;
	}
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
	
	
	
}
