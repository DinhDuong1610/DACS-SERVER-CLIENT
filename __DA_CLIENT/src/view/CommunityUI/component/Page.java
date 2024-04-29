package view.CommunityUI.component;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import controller.Community.Controller_Post;
import model.community.Model_Post;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;

public class Page extends JPanel{
	private News news;
	private NewPost newPost;
	private JPanel panel_post;
	private JPanel panel_event;
	private CardLayout cardLayout_Page;
	private Meets meets;
	private StartNewPost startNewPost;
	private Controller_Post action_post;
	private JDialog dialog;
	private Model_Project project;
	private JScrollPane sp;
	
	public Page(Model_Project project) {
		this.project = project;
		action_post = new Controller_Post(this);
		
		panel_post = new JPanel();
		panel_event = new JPanel();
		
		cardLayout_Page = new CardLayout();
		setLayout(cardLayout_Page);
		add(panel_post, "panel_post");
		add(panel_event, "panel_event");
		
		news = new News(project);
		newPost = new NewPost();
		meets = new Meets();
		
		newPost.getBt_newPost().addActionListener(action_post);
		
		panel_post.setLayout(new MigLayout("fillx", "0[fill]0", "0[120!]0[100%, fill]0"));
		
		panel_post.add(newPost, "wrap");
		sp = new JScrollPane(news);
		sp.setBorder(null);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_post.add(sp);	
		
		panel_event.setLayout(new MigLayout("fillx", "0[fill]0", "15[100%, fill]0"));
		JScrollPane sp2 = new JScrollPane(meets);
		sp2.setBorder(null);
		sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_event.add(sp2);
		
	}
	
	public void startNewPost() {
		startNewPost = new StartNewPost(Service.getInstance().getUser().getUserName());
		startNewPost.getBt_dangbai().addActionListener(action_post);
//		result = JOptionPane.showOptionDialog(this, startNewPost, null, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		dialog = new JDialog();
		dialog.setLayout(new GridLayout(1,1));
		dialog.setSize(800, 620);
		dialog.setLocationRelativeTo(null);
		dialog.add(startNewPost);
		dialog.setVisible(true);
	}
	
	public void postNewPost() {
		String userName = Service.getInstance().getUser().getUserName();
		String content = startNewPost.getTextArea().getText();
	
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String timing = dateFormat.format(currentTime);
        Model_Post post = new Model_Post(0, project.getProjectId(), userName, timing, content);
        Service.getInstance().postNews(post.toJsonObject());
//		news.post(post); 
        updateScroll();
		dialog.dispose();
	}
	
    public void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            sp.revalidate(); 
            sp.repaint(); 
        });
    }

	public CardLayout getCardLayout_Page() {
		return cardLayout_Page;
	}

	public void setCardLayout_Page(CardLayout cardLayout_Page) {
		this.cardLayout_Page = cardLayout_Page;
	}

	public NewPost getNewPost() {
		return newPost;
	}

	public StartNewPost getStartNewPost() {
		return startNewPost;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Model_Project getProject() {
		return project;
	}

	public void setProject(Model_Project project) {
		this.project = project;
	}
	
	
	
}
