package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel_Register extends JPanel{
	private JLabel label_user_avatar;
	private JLabel label_user_TenTaiKhoan;
	private JLabel label_user_HoVaTen;
	private JLabel label_user_NgaySinh;
	private JLabel label_user_email;
	private JLabel label_user_sdt;
	private JLabel label_user_DiaChi;
	private JTextField tf_user_TenTaiKhoan;
	private JTextField tf_user_HoVaTen;
	private JTextField tf_user_NgaySinh;
	private JTextField tf_user_email;
	private JTextField tf_user_sdt;
	private JScrollPane scrollPane;
	private JTextArea ta_DiaChi;
	private JLabel lb_tenTaiKhoan;
	private JLabel lblNewLabel;
	private JButton bt_chonAnh;
	private JButton bt_save;

	public Panel_Register() {
		setSize(1390, 585);
		setLayout(null);
		
		label_user_avatar = new JLabel("");
		label_user_avatar.setOpaque(true);
		label_user_avatar.setBackground(new Color(144, 238, 144));
		label_user_avatar.setBounds(83, 87, 350, 350);
		add(label_user_avatar);
		
		label_user_TenTaiKhoan = new JLabel("TÊN TÀI KHOẢN");
		label_user_TenTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_TenTaiKhoan.setBounds(503, 87, 217, 48);
		add(label_user_TenTaiKhoan);
		
		label_user_HoVaTen = new JLabel("HỌ VÀ TÊN");
		label_user_HoVaTen.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_HoVaTen.setBounds(503, 145, 217, 48);
		add(label_user_HoVaTen);
		
		label_user_NgaySinh = new JLabel("NGÀY SINH");
		label_user_NgaySinh.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_NgaySinh.setBounds(503, 203, 217, 48);
		add(label_user_NgaySinh);
		
		label_user_email = new JLabel("EMAIL");
		label_user_email.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_email.setBounds(503, 261, 217, 48);
		add(label_user_email);
		
		label_user_sdt = new JLabel("SỐ ĐIỆN THOẠI");
		label_user_sdt.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_sdt.setBounds(503, 319, 217, 48);
		add(label_user_sdt);
		
		label_user_DiaChi = new JLabel("ĐỊA CHỈ");
		label_user_DiaChi.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_user_DiaChi.setBounds(503, 377, 217, 48);
		add(label_user_DiaChi);
		
		tf_user_TenTaiKhoan = new JTextField();
		tf_user_TenTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tf_user_TenTaiKhoan.setColumns(10);
		tf_user_TenTaiKhoan.setBounds(745, 87, 575, 48);
		add(tf_user_TenTaiKhoan);
		
		tf_user_HoVaTen = new JTextField();
		tf_user_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tf_user_HoVaTen.setColumns(10);
		tf_user_HoVaTen.setBounds(745, 145, 575, 48);
		add(tf_user_HoVaTen);
		
		tf_user_NgaySinh = new JTextField();
		tf_user_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tf_user_NgaySinh.setColumns(10);
		tf_user_NgaySinh.setBounds(745, 203, 575, 48);
		add(tf_user_NgaySinh);
		
		tf_user_email = new JTextField();
		tf_user_email.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tf_user_email.setColumns(10);
		tf_user_email.setBounds(745, 261, 575, 48);
		add(tf_user_email);
		
		tf_user_sdt = new JTextField();
		tf_user_sdt.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tf_user_sdt.setColumns(10);
		tf_user_sdt.setBounds(745, 319, 575, 48);
		add(tf_user_sdt);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(745, 377, 575, 121);
		add(scrollPane);
		ta_DiaChi = new JTextArea();
		ta_DiaChi.setLineWrap(true);
		ta_DiaChi.setWrapStyleWord(true);
		ta_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scrollPane.setViewportView(ta_DiaChi);
		
		lb_tenTaiKhoan = new JLabel("AVATAR");
		lb_tenTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tenTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 30));
		lb_tenTaiKhoan.setBounds(83, 472, 350, 51);
		add(lb_tenTaiKhoan);
		
		lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(518, 10, 437, 48);
		add(lblNewLabel);
		
		bt_chonAnh = new JButton("");
		bt_chonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bt_chonAnh.setIcon(new ImageIcon(Panel_Register.class.getResource("/images/icon/icon_camera.png")));
		bt_chonAnh.setBounds(83, 436, 350, 36);
		add(bt_chonAnh);
		
		bt_save = new JButton("SAVE");
		bt_save.setFont(new Font("Tahoma", Font.BOLD, 25));
		bt_save.setBounds(597, 526, 167, 37);
		add(bt_save);
	}

	public JTextField getTf_user_TenTaiKhoan() {
		return tf_user_TenTaiKhoan;
	}

	public JTextField getTf_user_HoVaTen() {
		return tf_user_HoVaTen;
	}

	public JTextField getTf_user_NgaySinh() {
		return tf_user_NgaySinh;
	}

	public JTextField getTf_user_email() {
		return tf_user_email;
	}

	public JTextField getTf_user_sdt() {
		return tf_user_sdt;
	}

	public JButton getBt_chonAnh() {
		return bt_chonAnh;
	}
	
	
}
