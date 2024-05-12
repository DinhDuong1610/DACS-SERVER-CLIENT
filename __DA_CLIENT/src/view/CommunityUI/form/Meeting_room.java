package view.CommunityUI.form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import view.ChatUI.form.Menu_Left;

public class Meeting_room extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meeting_room frame = new Meeting_room();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Meeting_room() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-5,0, 1554, 850);
		setTitle("MEETING-ROOM");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0 ,0, 1554, 840);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_main = new JPanel();
		panel_main.setBounds(0, 0, 1540, 813);
		contentPane.add(panel_main);
		
		panel_main.setLayout(new MigLayout("fillx, filly", "0[300!]5[fill, 100%]0", "0[fill]0"));
		
		panel_main.add(new MenuLeft_Room());
		
		panel_main.add(new Screen());

	}
}
